package com.msgcopy.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by liang on 2017/3/3.
 */
public class CustomCircleView extends View implements Runnable {


    private static final String TAG = "CustomView";

    private Paint paint;

    private Context context;

    private float scrreenWidth;

    private float scrreenHeigth;

    private float radial = 20;

    private boolean first = true;

    public void setData(float x, float y){
        Log.i("haha",scrreenWidth+"--haha2--"+scrreenHeigth);
//        Toast.makeText(context, scrreenWidth+"--haha2--"+scrreenHeigth, Toast.LENGTH_SHORT).show();
        scrreenWidth = x;
        scrreenHeigth = y;
        postInvalidate();
    }

    public CustomCircleView(Context context) {
        super(context);
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        context = context;
        init();
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GRAY);

        /**
         * 画笔样式分三种： 1.Paint.Style.STROKE：描边 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);

//        scrreenWidth = getResources().getDisplayMetrics().widthPixels;
//        scrreenHeigth = getResources().getDisplayMetrics().heightPixels;

    }

    //在这里实现onTouchEvent
    public boolean onTouchEvent(MotionEvent event) {
        // 在这里判断一下如果是按下操作就获取坐标然后执行方法
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            scrreenWidth = event.getX();
            scrreenHeigth = event.getY();
            onButtonClickListener.onButtonClick(scrreenWidth, scrreenHeigth);
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            //移动
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            //松开
        }
        return super.onTouchEvent(event);
    }

    private OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        void onButtonClick(float width, float heigth);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!first){
            canvas.drawCircle(scrreenWidth, scrreenHeigth, radial, paint);
        }else {
            first = false;
        }
    }

    @Override
    public void run() {
        /**
         * 使用while循环不断的刷新view的半径
         * 当半径小于100每次增加10 invalidate()重绘view会报错
         * android.view.ViewRootImpl$CalledFromWrongThreadException 是非主线程更新UI
         * Android给提供postInvalidate();快捷方法来重绘view
         *  现在明白了invalidate和postInvalidate的小区别了吧
         */
        while (true) {
            if (radial <= 100) {
                radial += 2;
                postInvalidate();
            } else {
                paint.setStrokeWidth(0);
                radial = 0;
                postInvalidate();
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
