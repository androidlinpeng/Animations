package com.msgcopy.application;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FrameAnimationActivity extends AppCompatActivity {

    private ImageView imageView;
    private AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.frame);
        animation = (AnimationDrawable) imageView.getDrawable();
        animation.start();
    }
}
