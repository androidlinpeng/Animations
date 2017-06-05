package com.msgcopy.application.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.msgcopy.application.R;
import com.msgcopy.application.view.LoadAnimView;

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
