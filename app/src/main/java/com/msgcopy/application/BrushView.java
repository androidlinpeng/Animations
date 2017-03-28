package com.msgcopy.application;

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

    private Paint brush = new Paint();

    private Path path = new Path();

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


    private void init() {

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