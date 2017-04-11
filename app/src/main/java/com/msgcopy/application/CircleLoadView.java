package com.msgcopy.application;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by liang on 2017/4/10.
 */

public class CircleLoadView extends View {

    private static final String TAG = "CircleLoadView";

    /**
     * 第一圈的颜色
     */
    private int mFirstColor;
    /**
     * 第二圈的颜色
     */
    private int mSecondColor;
    /**
     * 圈的宽度
     */
    private int mCircleWidth;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 当前进度
     */
    private int mProgress;

    private int mTvProgress;

    /**
     * 速度
     */
    private int mSleep;

    /**
     * 是否应该开始下一个
     */
    private boolean isNext = false;

    public interface OnProgressChange{
        void OnProgressChange(int progress);
    }
    private OnProgressChange onProgressChangeListener=null;

    public void setOnProgressChangeListener(OnProgressChange l){
        this.onProgressChangeListener=l;
    }

    public void updateProgress(int p) {
        mProgress = p;

        if (mProgress == 360) {
            mProgress = 0;
        }else {
            mTvProgress = mProgress*100/360;
            Log.i("progress",""+mTvProgress);
            postInvalidate();
        }

    }


    public CircleLoadView(Context context) {
        super(context);
        Log.i(TAG, "context");
    }

    public CircleLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "context, attrs");
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleLoadView);
        int n = a.getIndexCount();

        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.CircleLoadView_firstcolor:
                    mFirstColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CircleLoadView_secondcolor:
                    mSecondColor = a.getColor(attr, Color.GREEN);
                    break;
                case R.styleable.CircleLoadView_circleWidth:
                    mCircleWidth = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CircleLoadView_sleep:
                    mSleep = a.getInt(attr, 20);// 默认20
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();

//        // 绘图线程
//        new Thread() {
//            public void run() {
//                while (true) {
//                    if (mProgress == 360) {
//                        mProgress = 0;
//                        break;
//                    }
//                    mTvProgress = mProgress/360*100;
//                    Log.i("progress",""+mTvProgress);
//                    postInvalidate();
//                    try {
//                        Thread.sleep(mSleep);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
    }

    public CircleLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "context, attrs, defStyleAttr");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(null!=onProgressChangeListener){
            onProgressChangeListener.OnProgressChange(mTvProgress);
        }

        int centre = getWidth() / 2; // 获取圆心的x坐标
        int radius = centre - mCircleWidth / 2;// 半径
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        RectF rectf = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        canvas.drawCircle(centre, centre, radius, mPaint); // 画出圆环
        mPaint.setColor(mSecondColor); // 设置圆环的颜色
        canvas.drawArc(rectf, -90, mProgress, false, mPaint); // 根据进度画圆弧

    }
}
