package com.msgcopy.application.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.msgcopy.application.R;
import com.msgcopy.application.ViewUtils;

/**
 * Created by liang on 2017/6/7.
 */

public class FicheView extends View {

    private static final String TAG = "FicheView";

    private Context mContext;

    private Paint mBackgroundPaint;
    private int mBackgroundCorner;//背景四角的弧度

    private int mWidth;//自定义View宽
    private int mHeight;//自定义View高

    public FicheView(Context context) {
        super(context);
        Log.i(TAG, "FicheView 1");
        mContext = context;
        init();
    }

    public FicheView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "FicheView 2");
        mContext = context;
        init();
    }

    public FicheView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "FicheView 3");
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(getResources().getColor(R.color.white));

        mBackgroundCorner = ViewUtils.dp2px(mContext, 5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackgrouund(0, 0, mWidth, mHeight, mBackgroundCorner, canvas, mBackgroundPaint);
    }

    private void drawBackgrouund(int left, int top, int right, int bottom, int radius, Canvas canvas, Paint paint) {
        Path path = new Path();

        path.moveTo(left, top);

        path.lineTo(right - radius, top);
        path.quadTo(right, top, right, top + radius);

        path.lineTo(right, bottom);

        path.lineTo(left, bottom);

        path.lineTo(left, top + radius);
        path.quadTo(left, top, left + radius, top);

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}











