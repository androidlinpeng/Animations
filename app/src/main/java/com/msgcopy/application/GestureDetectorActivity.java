package com.msgcopy.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class GestureDetectorActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // e1: 第一次按下的位置   e2:  当手离开屏幕 时的位置  velocityX:  沿x 轴的速度  velocityY： 沿Y轴方向的速度
                //判断竖直方向移动的大小

                //判断竖直方向移动的大小
                if (Math.abs(e1.getRawY() - e2.getRawY()) > 100) {
//                    Toast.makeText(GestureDetectorActivity.this, "动作不合法", Toast.LENGTH_SHORT).show();
                    return true;
                }
                if (Math.abs(velocityX) < 100) {
//                    Toast.makeText(GestureDetectorActivity.this, "移动的太慢", Toast.LENGTH_SHORT).show();
                    return true;
                }

                if ((e1.getRawX() - e2.getRawX()) > 200) {// 表示 向右滑动表示下一页
                    Toast.makeText(GestureDetectorActivity.this, "滑动下一页", Toast.LENGTH_SHORT).show();
                    //显示下一页
                    return true;
                }

                if ((e2.getRawX() - e1.getRawX()) > 200) {  //向左滑动 表示 上一页
                    Toast.makeText(GestureDetectorActivity.this, "滑动显示上一页", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplication(),MainActivity.class));
                    overridePendingTransition(R.anim.leftin,R.anim.leftout);
                    //显示上一页
                    return true;//消费掉当前事件  不让当前事件继续向下传递
                }

                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        gestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }
}
