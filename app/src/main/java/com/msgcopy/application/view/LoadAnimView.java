package com.msgcopy.application.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.msgcopy.application.R;

/**
 * Created by liang on 2017/4/7.
 */

public class LoadAnimView extends View {

    private Paint mPaint;

    private float radial1 = 0;
    private float radial2 = 0;
    private float radial3 = 0;

    private float Minradial = 0;
    private float Maxradial = 30;

    private long sleepTime = 25;

    private boolean bl1 = true;
    private boolean bl2 = true;
    private boolean bl3 = true;

    public LoadAnimView(Context context) {
        super(context);
        init();
    }


    public LoadAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public void init() {
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.colorAccent));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth((float) 0.1);

    }

    public void initLoad() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (radial1 <= Maxradial && bl1) {
                                radial1 += 1;
                                postInvalidate();
                                if (radial1 == Maxradial) {
                                    bl1 = false;
                                }
                            } else {
                                radial1 -= 1;
                                postInvalidate();
                                if (radial1 == Minradial) {
                                    bl1 = true;
                                }
                            }
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (radial2 <= Maxradial && bl2) {
                                radial2 += 1;
                                postInvalidate();
                                if (radial2 == Maxradial) {
                                    bl2 = false;
                                }
                            } else {
                                radial2 -= 1;
                                postInvalidate();
                                if (radial2 == Minradial) {
                                    bl2 = true;
                                }
                            }
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }, 800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            if (radial3 <= Maxradial && bl3) {
                                radial3 += 1;
                                postInvalidate();
                                if (radial3 == Maxradial) {
                                    bl3 = false;
                                }
                            } else {
                                radial3 -= 1;
                                postInvalidate();
                                if (radial3 == Minradial) {
                                    bl3 = true;
                                }
                            }
                            try {
                                Thread.sleep(sleepTime);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth() / 4, getMeasuredHeight() / 2, radial1, mPaint);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, radial2, mPaint);
        canvas.drawCircle(getMeasuredWidth() * 3 / 4, getMeasuredHeight() / 2, radial3, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
