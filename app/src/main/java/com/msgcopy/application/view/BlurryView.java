package com.msgcopy.application.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liang on 2017/4/1.
 */

public class BlurryView extends View{


    public BlurryView(Context context) {
        super(context);
    }

    public BlurryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BlurryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        int mDownsampleFactor;
        int mOverlayColor;

        View mBlurredView;
        int mBlurredViewWidth, mBlurredViewHeight;

        boolean mDownsampleFactorChanged;
        Bitmap mBitmapToBlur, mBlurredBitmap;
        Canvas mBlurringCanvas;
//        RenderScript mRenderScript;
//        ScriptIntrinsicBlur mBlurScript = null;
//        Allocation mBlurInput, mBlurOutput;

//        if (mBlurScript != null){
//            mBlurScript.destroy();
//        }


    }














}
