package com.msgcopy.application.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.widget.Toast;

import com.msgcopy.application.R;
import com.msgcopy.application.view.CustomCircleView;

public class CustomCircleActivity extends AppCompatActivity implements CustomCircleView.OnButtonClickListener{

    private static final String TAG = "CustomCircleActivity";
    
    private CustomCircleView customView = null;

    private float scrreenWidth;

    private float scrreenHeigth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        //取消标题

        customView = (CustomCircleView)findViewById(R.id.customView);
        customView.setOnButtonClickListener(this);
    }

    private void onClickView(float x, float y) {
        customView.setData(x,y);
        new Thread(customView).start();
    }

    //在这里实现onTouchEvent
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onClickView(scrreenWidth,scrreenHeigth);
            Toast.makeText(this, scrreenHeigth+"========="+scrreenWidth, Toast.LENGTH_SHORT).show();
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void onButtonClick(float width, float heigth) {
        scrreenWidth = width;
        scrreenHeigth = heigth;
    }
}
