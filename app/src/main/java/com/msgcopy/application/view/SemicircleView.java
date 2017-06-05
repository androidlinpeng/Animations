package com.msgcopy.application.view;

import android.content.Context;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liang on 2017/3/29.
 */

public class SemicircleView extends View{

    private Path path;

    public SemicircleView(Context context) {
        super(context);
    }

    public SemicircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SemicircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
