package com.msgcopy.application.activity;

import android.app.Application;

/**
 * Created by liang on 2017/5/11.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
    public static MyApplication getInstance() {
        return myApplication;
    }
}
