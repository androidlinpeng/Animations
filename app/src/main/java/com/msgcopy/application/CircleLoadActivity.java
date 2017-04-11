package com.msgcopy.application;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CircleLoadActivity extends AppCompatActivity {

    private CircleLoadView circle_load;

    private TextView progress;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                circle_load.updateProgress(msg.getData().getInt("p"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_load);

        circle_load = (CircleLoadView) findViewById(R.id.circle_load);
        progress = (TextView)findViewById(R.id.progress);
        circle_load.setOnProgressChangeListener(new CircleLoadView.OnProgressChange() {
            @Override
            public void OnProgressChange(int p) {
                progress.setText(p+"%");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i<=360){
                    i+= 1;
                    Message message=new Message();
                    Bundle bundle=new Bundle();
                    bundle.putInt("p", i);
                    message.setData(bundle);//bundle传值，耗时，效率低
                    message.what=1;//标志是哪个线程传数据
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
