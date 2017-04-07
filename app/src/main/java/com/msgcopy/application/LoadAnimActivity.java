package com.msgcopy.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoadAnimActivity extends AppCompatActivity {

    private LoadAnimView loadAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_anim);

        loadAnim = (LoadAnimView)findViewById(R.id.loadAnimview);
        loadAnim.initLoad();

    }
}
