package com.msgcopy.application.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liang on 2017/3/10.
 */
public class BrushView extends View {

    private Paint brush;

    private Path path;

    private Canvas cacheCanvas;

    private Bitmap cachebBitmap;

    public BrushView(Context context) {
        super(context);
        init();
    }

    public BrushView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BrushView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void emptyPalette(){
        init();
        postInvalidate();
    }

    private void init() {

        brush = new Paint();
        path = new Path();

        brush.setAntiAlias(true);
        brush.setColor(Color.BLACK);
        brush.setStyle(Paint.Style.STROKE);// stroke轮廓，fill填充
        brush.setStrokeJoin(Paint.Join.ROUND);// 设置绘制时各图形的结合方式，如平滑效果等
        brush.setStrokeCap(Paint.Cap.ROUND);//当style不是fill时，图形样式
        brush.setStrokeWidth(2f);
        //注意下面，用来存图像
        cachebBitmap = Bitmap.createBitmap(1000, 1600, Bitmap.Config.ARGB_8888 );
        cacheCanvas = new Canvas(cachebBitmap);
        cacheCanvas.drawColor(Color.WHITE);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

//        ACTION_DOWN	手指 初次接触到屏幕 时触发。
//        ACTION_MOVE	手指 在屏幕上滑动 时触发，会多次触发。
//        ACTION_UP	手指 离开屏幕 时触发。
//        ACTION_CANCEL	事件 被上层拦截 时触发。
//        ACTION_OUTSIDE	手指 移除控件区域 时触发。

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                cacheCanvas.drawPath(path, brush);//抬起时，才画线
                //path.reset();//对画布以外的部分有影响
                break;
        }
        // Force a view to draw again
        //postInvalidate();
        invalidate();
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(cachebBitmap, 0, 0, null);
        canvas.drawPath(path, brush);
    }

}