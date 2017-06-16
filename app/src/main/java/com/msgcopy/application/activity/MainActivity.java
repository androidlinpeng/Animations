package com.msgcopy.application.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.msgcopy.application.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                startActivity(new Intent(this,AnimationActivity.class));
                overridePendingTransition(R.anim.zoomin,R.anim.zooout);
                break;
            case R.id.button2:
                startActivity(new Intent(this,AnimationActivity.class));
                overridePendingTransition(0,R.anim.zooout);
                break;
            case R.id.button3:
                startActivity(new Intent(this,GestureDetectorActivity.class));
                overridePendingTransition(R.anim.leftin,R.anim.leftout);
                break;
            case R.id.button4:

                break;
            case R.id.button6:
                startActivity(new Intent(this,CustomCircleActivity.class));
                break;
            case R.id.button7:
                startActivity(new Intent(MainActivity.this,PaletteViewActivity.class));
                break;
            case R.id.button9:
                startActivity(new Intent(MainActivity.this,ProgressViewActivity.class));
                break;
            case R.id.blurry:
                startActivity(new Intent(MainActivity.this,BlurryActivity.class));
                break;
            case R.id.frame:
                startActivity(new Intent(MainActivity.this,FrameAnimationActivity.class));
                break;
            case R.id.loadAnim:
                startActivity(new Intent(MainActivity.this,LoadAnimActivity.class));
                break;
            case R.id.property:
                startActivity(new Intent(MainActivity.this,PropertyAnimActivity.class));
                break;
            case R.id.circle_load:
                startActivity(new Intent(MainActivity.this,CircleLoadActivity.class));
                break;
            case R.id.fiche:
                startActivity(new Intent(MainActivity.this,FicheActivity.class));
                break;
        }
    }
}
