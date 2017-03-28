package com.msgcopy.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button5:
                finish();
                overridePendingTransition(0,R.anim.zooout);
                break;
        }
    }
}
