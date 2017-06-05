package com.msgcopy.application.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.msgcopy.application.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/5/11.
 */

public class SectorView extends RelativeLayout {

    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private List<ImageView> mlist;
    private ImageView[] image;

    private Context context;

    public SectorView(Context context) {
        super(context);
        this.context = context;
    }

    public SectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.view_anim_sector, this);
        initView(view);
    }

    public void initView(View view) {

        imageView3 = (ImageView) view.findViewById(R.id.imageView3);
        imageView4 = (ImageView) view.findViewById(R.id.imageView4);
        imageView5 = (ImageView) view.findViewById(R.id.imageView5);
        imageView6 = (ImageView) view.findViewById(R.id.imageView6);
        imageView7 = (ImageView) view.findViewById(R.id.imageView7);
        imageView8 = (ImageView) view.findViewById(R.id.imageView8);

//        image = new
//        image[] = (ImageView) view.findViewById(R.id.imageView3);
//        imageView4 = (ImageView) view.findViewById(R.id.imageView4);
//        imageView5 = (ImageView) view.findViewById(R.id.imageView5);
//        imageView6 = (ImageView) view.findViewById(R.id.imageView6);
//        imageView7 = (ImageView) view.findViewById(R.id.imageView7);
//        imageView8 = (ImageView) view.findViewById(R.id.imageView8);

        mlist = new ArrayList<ImageView>();
        mlist.add(imageView3);
        mlist.add(imageView4);
        mlist.add(imageView5);
        mlist.add(imageView6);
        mlist.add(imageView7);
        mlist.add(imageView8);

        double left = imageView3.getLeft();
        double top = imageView3.getTop();
        double right = imageView3.getRight();
        double bottom = imageView3.getBottom();
        Log.i("initViewItem", "left:" + left + "   top:" + top + "   right:" + right + "   bottom1:" + bottom);
        double radius = this.getHeight() / 2;
        double angle = 0;
        double variate = 180 / (mlist.size() - 1);

        for (int i = 0; i < mlist.size(); i++) {
            //将角度转换为弧度Math.toRadians(angle)
            initViewItem(mlist.get(i), left, top, right, bottom, Math.toRadians(angle), radius);
            angle = angle - variate;
        }
    }

    private void initViewItem(ImageView imageView, double left, double top, double right, double bottom, double angle, double radius) {

        double left1 = left + radius * Math.cos(angle);
        double top1 = top + radius * Math.sin(angle);
        double right1 = right + radius * Math.cos(angle);
        double bottom1 = bottom + radius * Math.sin(angle);

//        Log.i("initViewItem2","left:"+radius*Math.cos(angle)+"   top:"+radius*Math.sin(angle)+"   right:"+radius*Math.cos(angle)+"   bottom1:"+radius*Math.sin(angle));

        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(imageView.getLayoutParams());
        margin.setMargins((int) left1, (int) top1, (int) right1, (int) bottom1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        imageView.setLayoutParams(layoutParams);


//        PropertyValuesHolder a = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
//        PropertyValuesHolder r = PropertyValuesHolder.ofFloat("rotationY", 0.0f, 360.0f, 0.0f);
//        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", 0.0f, (float) left1);
//        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("translationY", 0.0f, (float) top1);
//        ofPropertyValuesHolder(imageView, a, x, y).setDuration(2000).start();
//
//        Log.i("initViewItem1", "(float)left:" + (float) left + "   (float) top:" + (float) top + "   (float) left1:" + (float) left1 + "   (float) top1:" + (float) top1);
//        Log.i("initViewItem1","(int)left1:"+(int)left1+"   (int)top1:"+(int)top1+"   (int)right1:"+(int)right1+"   (int)bottom1:"+(int)bottom1);
    }
}
