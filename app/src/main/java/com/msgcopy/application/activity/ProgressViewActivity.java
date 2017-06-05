package com.msgcopy.application.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.msgcopy.application.R;
import com.msgcopy.application.view.WaveView;

public class ProgressViewActivity extends AppCompatActivity {

    private WaveView waveView;
    private TextView progress;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                waveView.updateProgress(msg.getData().getFloat("p"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_view);

        waveView = (WaveView)findViewById(R.id.waveView);
        progress = (TextView) findViewById(R.id.progress);
        waveView.setOnProgressChangeListener(new WaveView.OnProgressChange() {
            @Override
            public void onProgressChange(int p) {
                progress.setText(p+"%");
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                float i = (float) 0.0;
                while (i<=1){
                    i+= 0.005;
                    Message message=new Message();
                    Bundle bundle=new Bundle();
                    bundle.putFloat("p", i);
                    message.setData(bundle);//bundle传值，耗时，效率低
                    message.what=1;//标志是哪个线程传数据
                    mHandler.sendMessage(message);//发送message信息
                    //message有四个传值方法，
                    //两个传int整型数据的方法message.arg1，message.arg2
                    //一个传对象数据的方法message.obj
                    //一个bandle传值方法
                    try {
                        Thread.sleep(100);//不加程序跑的太快反应不过来
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

//        new InitDataTask(TASK_INIT_DATA).execute();

    }


    private static final int TASK_INIT_DATA = 1;

    private class InitDataTask extends AsyncTask<Object, Float, String> {

        private int taskId = -1;

        public InitDataTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        protected String doInBackground(Object... params) {
            switch (this.taskId) {
                case TASK_INIT_DATA:

                    publishProgress(0.12f);
                    publishProgress(0.24f);
                    publishProgress(0.36f);
                    publishProgress(0.48f);
                    publishProgress(0.60f);
                    publishProgress(0.72f);
                    publishProgress(0.84f);
                    publishProgress(1.0f);

                    break;
                default:
                    break;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            waveView.updateProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String data) {

        }
    }
}
