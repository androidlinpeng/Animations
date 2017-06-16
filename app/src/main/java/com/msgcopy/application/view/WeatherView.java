package com.msgcopy.application.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.msgcopy.application.R;

/**
 * Created by liang on 2017/6/5.
 */

public class WeatherView extends View {

    private static final String TAG = "HealthView";

    private int mWidth;//自定义View宽
    private int mHeight;//自定义View高

    private Context mContext;
    private Paint mArcPaint;//圆弧的画笔
    private Paint mTextPaint;//圆弧文字的画笔

    private RectF mArcRect;
    private int mCircleWidth;

    private int index = 0;
    private float sweepAngle = 0;
    private int colorInt = 0;

    public void UpdateIndex(float index,int colorInt){
        this.index = (int) index;
        this.sweepAngle = index*270/500;
        this.colorInt = colorInt;
        init();
        postInvalidate();
    }

    public WeatherView(Context context) {
        super(context);
        Log.i(TAG, "HealthView1");
        init();
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "HealthView2");
        init();
    }

    public WeatherView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "HealthView3");
        mContext = context;
        init();
    }

    private void init() {

        mCircleWidth = (int) getResources().getDimension(R.dimen.view_arc_wide);
        mArcPaint = new Paint();
        mArcPaint.setStrokeWidth(mCircleWidth);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setColor(getResources().getColor(R.color.gray));
        mArcPaint.setStrokeJoin(Paint.Join.ROUND);//在画笔的连接处是圆滑的
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);//在画笔的起始处是圆滑的

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);//文字居中

    }

    //当view的大小发生变化时触发
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centre = mWidth / 2; // 获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;// 半径
        float xPos;
        float yPos;

        //圆弧范围
        mArcRect = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        //初始化的圆弧
        canvas.drawArc(mArcRect, 135, 270, false, mArcPaint);

        //加载后的圆弧
        mArcPaint.setColor(colorInt);
        canvas.drawArc(mArcRect, 135, sweepAngle, false, mArcPaint);

        //文字绘画AQI
        xPos = mWidth/2;
        yPos = mHeight/2;
        mTextPaint.setColor(colorInt);
        mTextPaint.setTextSize((int) getResources().getDimension(R.dimen.view_text_size_l));
        String str_aqi1 = index+" ";
        canvas.drawText(str_aqi1, xPos,yPos , mTextPaint);

        //文字绘画AQI
        yPos = mHeight*2/3;
        mTextPaint.setColor(getResources().getColor(R.color.black));
        mTextPaint.setTextSize((int) getResources().getDimension(R.dimen.view_text_size_s));
        String str_aqi = getResources().getString(R.string.str_aqi);
        canvas.drawText(str_aqi, xPos, yPos, mTextPaint);

        //文字绘画
        yPos = mHeight*9/10;
        mTextPaint.setColor(getResources().getColor(R.color.blackDark));
        mTextPaint.setTextSize((int) getResources().getDimension(R.dimen.view_text_size_m));
        String text = getResources().getString(R.string.str_weather);
        canvas.drawText(text, xPos, yPos, mTextPaint);
    }
}









