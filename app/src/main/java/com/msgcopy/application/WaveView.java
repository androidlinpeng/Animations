package com.msgcopy.application;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by liang on 2017/3/30.
 */

public class WaveView extends View {

    public interface OnProgressChange{
        public void onProgressChange(int progress);
    }

    private OnProgressChange onProgressChangeListener=null;

    private Paint wavePaint;
    //    private int waveColor = 0xff0099CC;
    private int waveColor = 0xffFFFFFF;
    private Path path;

    // 左右偏移 φ
    private int fai = 0;
    // 上下偏移
    private float k = -50;
    // 角速度
    private float w = 3.0f;
    // 振幅
    private int a = 12;

//    // 左右偏移 φ
//    private int fai = 0;
//    // 上下偏移
//    private float k = -50;
//    // 角速度
//    private float w = 0.5f;
//    // 振幅
//    private int a = 15;

    private int height;
    private int width;
    private float targetHeight;
    private int progress = 0;
    // 0% 时，空白的高度
    private float baseBlank;

    private int ms = 3;

    private boolean isRun = true;

    public WaveView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Log.i("WaveView","context, attrs, defStyle");
        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("WaveView","context, attrs");
        init();
    }

    public WaveView(Context context) {
        super(context);
        Log.i("WaveView","context");
        init();
    }

    private void init() {
        wavePaint = new Paint();
        wavePaint.setAntiAlias(true);
        wavePaint.setColor(waveColor);

        path = new Path();

        new MyThread().start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(null!=onProgressChangeListener){
            onProgressChangeListener.onProgressChange(progress);
        }
        setPath();
        canvas.drawPath(path, wavePaint);
    }

    public void setOnProgressChangeListener(OnProgressChange l){
        this.onProgressChangeListener=l;
    }

    private void setPath(){
        int x = 0;
        int y = 0;
        path.reset();
        for (int i = 0; i < width; i++) {
            x = i;
            y = (int) (a * Math.sin((i * w + fai) * Math.PI / 180) + k);
            if (i == 0) {
                path.moveTo(x, y);
            }
            path.quadTo(x, y, x + 1, y);
        }
        path.lineTo(width, 0);
        path.lineTo(0, 0);
        path.close();

    }

    /**
     *
     * @param p 0~1
     */
    public void updateProgress(float p) {
        if(p >=0 && p <= 1){
            targetHeight = (float) (baseBlank * (1 - p));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(height==0 && width==0){
            initLayoutParams();
        }
    }

    private void initLayoutParams(){
        height = this.getHeight();
        width = this.getWidth();
        baseBlank = (float) (height * 0.9);
        targetHeight = baseBlank;
        k = baseBlank;
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            while (isRun) {
                fai++;
                if (k > targetHeight) {
                    k -= 0.5;
                    progress = (int) ((baseBlank - k) / baseBlank * 100);
                }
                if (progress >= 100) {
                    isRun = false;
                }
                if (fai == 360) {
                    fai = 0;
                }
                mHandler.sendEmptyMessage(1);
                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                invalidate();
            }
        }
    };

}
