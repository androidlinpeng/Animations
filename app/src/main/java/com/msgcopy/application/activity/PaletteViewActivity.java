package com.msgcopy.application.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.msgcopy.application.FileUtils;
import com.msgcopy.application.R;
import com.msgcopy.application.view.BrushView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PaletteViewActivity extends AppCompatActivity {

    private static final String TAG = "PaletteViewActivity";

    private ImageView imageView;

    private BrushView brushView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_view);

        imageView = (ImageView) findViewById(R.id.imageView);
        brushView = (BrushView) findViewById(R.id.brush);
        Button button = (Button) findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //View转换为Bitmap及getDrawingCache=null的解决方法
                brushView.buildDrawingCache();//构建绘图缓存
                Bitmap bitmap = Bitmap.createBitmap(brushView.getDrawingCache());
                imageView.setImageBitmap(bitmap);
                brushView.setDrawingCacheEnabled(false);
                try {
                    File imgFile= FileUtils.createTmpFile("view.png");
                    FileOutputStream stream = new FileOutputStream(imgFile);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                    stream.flush();
                    stream.close();
                    Log.i(TAG, "imgFile" + imgFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onClick(View view){
        brushView.emptyPalette();
    }
}

