package com.msgcopy.application.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.msgcopy.application.R;
import com.msgcopy.application.view.CircleLoadView;
import com.msgcopy.application.view.WeatherView;

public class CircleLoadActivity extends AppCompatActivity {

    private CircleLoadView circle_load;
    private WeatherView weather_arc;

    private TextView progress;
    private int[] mBgColors;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                circle_load.updateProgress(msg.getData().getInt("p"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_load);


        weather_arc = (WeatherView) findViewById(R.id.weather_arc);
        weather_arc.UpdateIndex(80, getColors()[0]);

        circle_load = (CircleLoadView) findViewById(R.id.circle_load);
        progress = (TextView) findViewById(R.id.progress);
        circle_load.setOnProgressChangeListener(new CircleLoadView.OnProgressChange() {
            @Override
            public void OnProgressChange(int p) {
                progress.setText(p + "%");
            }
        });

        chengProgress();

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_g:
                weather_arc.UpdateIndex(10, getColors()[0]);
                break;
            case R.id.bt_o:
                weather_arc.UpdateIndex(150, getColors()[1]);
                break;
            case R.id.bt_r:
                weather_arc.UpdateIndex(300, getColors()[2]);
                break;
        }
    }

    private int[] getColors() {
        if (mBgColors == null) {
            Resources resources = getResources();
            mBgColors = new int[]{
                    resources.getColor(R.color.green),
                    resources.getColor(R.color.orange),
                    resources.getColor(R.color.red)
            };
        }
        return mBgColors;
    }

    private void chengProgress() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i <= 360) {
                    i += 1;
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("p", i);
                    message.setData(bundle);//bundle传值，耗时，效率低
                    message.what = 1;//标志是哪个线程传数据
                    mHandler.sendMessage(message);//发送message信息
                    try {
                        Thread.sleep(50);//不加程序跑的太快反应不过来
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
