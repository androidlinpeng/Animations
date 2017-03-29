package com.msgcopy.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class GestureDetectorActivity extends AppCompatActivity {

    private float wide,high;

    private GestureDetector gestureDetector;

    private TextView popup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        popup = (TextView)findViewById(R.id.popup);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // e1: 第一次按下的位置   e2:  当手离开屏幕 时的位置  velocityX:  沿x 轴的速度  velocityY： 沿Y轴方向的速度

//                //判断竖直方向移动的大小
//                if (Math.abs(e1.getRawY() - e2.getRawY()) > 100) {
//                    Toast.makeText(GestureDetectorActivity.this, "动作不合法", Toast.LENGTH_SHORT).show();
//                    return true;
//                }

//                if (Math.abs(velocityX) < 100) {
//                    Toast.makeText(GestureDetectorActivity.this, "移动的太慢", Toast.LENGTH_SHORT).show();
//                    return true;
//                }

                //向右滑动
                if ((e1.getRawX() - e2.getRawX()) > 200) {
                    Toast.makeText(GestureDetectorActivity.this, "滑动下一页", Toast.LENGTH_SHORT).show();

                    return true;
                }

                //向左滑动
                if ((e2.getRawX() - e1.getRawX()) > 200) {
                    Toast.makeText(GestureDetectorActivity.this, "滑动显示上一页", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplication(),MainActivity.class));
                    overridePendingTransition(R.anim.leftin,R.anim.leftout);

                    return true;
                }

                //向下滑动
                if ((e2.getRawY() - e1.getRawY()) > 10){
                    Toast.makeText(GestureDetectorActivity.this, "向下滑动", Toast.LENGTH_SHORT).show();
                    if (high < 600){
                        Toast.makeText(GestureDetectorActivity.this, "high"+high, Toast.LENGTH_SHORT).show();
                        popupWindow();
                    }

                    return true;//消费掉当前事件  不让当前事件继续向下传递
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        wide = event.getX();
        high = event.getY();

        gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    public void popupWindow(){

        LayoutInflater mInflater = getLayoutInflater();
        View view = mInflater.inflate(R.layout.view_popupwindow, null);
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(view);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        popupWindow.showAsDropDown(popup, 0, -popup.getHeight());
//        popupWindow.showAtLocation((ViewGroup) popup.getParent(), Gravity.CENTER_HORIZONTAL, 0, 0);
    }

}
