package com.msgcopy.application.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.msgcopy.application.R;
import com.msgcopy.application.view.SectorView;

import static android.animation.ObjectAnimator.ofPropertyValuesHolder;

public class PropertyAnimActivity extends AppCompatActivity {

    private int width;
    private int height;
    private ImageView imageView2;
    private SectorView sector;

//    private ImageView imageView3;
//    private ImageView imageView4;
//    private ImageView imageView5;
//    private ImageView imageView6;
//    private ImageView imageView7;
//    private ImageView imageView8;
//    private List<ImageView> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_anim);

        WindowManager wm = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth()/2;
        height = wm.getDefaultDisplay().getHeight() / 2;

        sector = (SectorView) findViewById(R.id.sector);
        imageView2 = (ImageView) findViewById(R.id.imageView2);

//        imageView3 = (ImageView) findViewById(R.id.imageView3);
//        imageView4 = (ImageView) findViewById(R.id.imageView4);
//        imageView5 = (ImageView) findViewById(R.id.imageView5);
//        imageView6 = (ImageView) findViewById(R.id.imageView6);
//        imageView7 = (ImageView) findViewById(R.id.imageView7);
//        imageView8 = (ImageView) findViewById(imageView8);
//
//        mlist = new ArrayList<ImageView>();
//        mlist.add(imageView3);
//        mlist.add(imageView4);
//        mlist.add(imageView5);
//        mlist.add(imageView6);
//        mlist.add(imageView7);
//        mlist.add(imageView8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button11:

                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.8f, 1f);
                PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.8f, 1f);
                ofPropertyValuesHolder(view, pvhX, pvhY, pvhZ).setDuration(800).start();

                ValueAnimator animator = ValueAnimator.ofFloat(0, height);
                animator.setTarget(imageView2);
                animator.setDuration(1000).start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        imageView2.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });

                break;
            case R.id.button12:
                PropertyValuesHolder pvX = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
                PropertyValuesHolder pv = PropertyValuesHolder.ofFloat("translationY", 0.0f, 10.0f);
                ObjectAnimator animator1 = ofPropertyValuesHolder(view, pvX, pv);

                PropertyValuesHolder pv1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
                PropertyValuesHolder pv2 = PropertyValuesHolder.ofFloat("translationY", 10.0f, 0.0f);
                ObjectAnimator animator2 = ofPropertyValuesHolder(view, pv1, pv2);

                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.playTogether(animator1, animator2);//animator2和animator1同时
//                animatorSet.play(animator1).with(animator2);//animator2和animator1同时
                animatorSet.play(animator2).after(animator1);//animator2在animator1之后
                animatorSet.setDuration(500);
                animatorSet.start();


                ValueAnimator anima = new ValueAnimator();
                anima.setDuration(3000);
                anima.setObjectValues(new PointF(0, 0));
                anima.setInterpolator(new LinearInterpolator());
                anima.setEvaluator(new TypeEvaluator<PointF>() {
                    @Override
                    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                        // x方向200px/s ，则y方向0.5 * g * t (g = 100px / s*s)
                        PointF point = new PointF();
                        point.x = 200 * fraction * 3;
                        point.y = 0.5f * 100 * (fraction * 3) * (fraction * 3);
                        return point;
                    }
                });
                anima.start();
                anima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF point = (PointF) animation.getAnimatedValue();
                        imageView2.setX(point.x);
                        imageView2.setY(point.y);
                    }
                });

                break;
            case R.id.button13:

                PropertyValuesHolder p = PropertyValuesHolder.ofInt("backgroundColor", 0xffffff00, 0xffffffff);
                PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
                PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.8f, 1f);
                PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.8f, 1f);
                ofPropertyValuesHolder(view, p, pvh1, pvh2, pvh3).setDuration(2000).start();


                PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
                PropertyValuesHolder tlY = PropertyValuesHolder.ofFloat("translationY", 0.0f, -100.0f);
                PropertyValuesHolder tlX = PropertyValuesHolder.ofFloat("translationX", 0.0f, 100.0f);
                PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.0f);
                PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f, 0.0f);
                ObjectAnimator anim = ofPropertyValuesHolder(imageView2, alpha,tlX,tlY,scaleX,scaleY);
                anim.setDuration(1000).start();
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewGroup parent = (ViewGroup) imageView2.getParent();
                        if (parent != null)
                            parent.removeView(imageView2);
                    }
                });

                anim.addListener(new Animator.AnimatorListener() {

                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        ViewGroup parent = (ViewGroup) imageView2.getParent();
                        if (parent != null)
                            parent.removeView(imageView2);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        // TODO Auto-generated method stub
                    }
                });
                anim.start();

                break;
            case R.id.button14:
                PropertyValuesHolder pp = PropertyValuesHolder.ofFloat("rotationX", 0.0f, 360.0f, 0.0f);
                ofPropertyValuesHolder(view, pp).setDuration(2000).start();

//                double left = imageView3.getLeft();
//                double top = imageView3.getTop();
//                double right = imageView3.getRight();
//                double bottom = imageView3.getBottom();
//                Log.i("initViewItem","left:"+left+"   top:"+top+"   right:"+right+"   bottom1:"+bottom);
//                double radius = width-imageView3.getWidth();
//                double angle = 0;
//                double variate = 180/(mlist.size()-1);
//
//                for (int i = 0; i < mlist.size(); i++) {
//                    //将角度转换为弧度Math.toRadians(angle)
//                    initViewItem(mlist.get(i),left,top,right,bottom,Math.toRadians(angle),radius);
//                    angle = angle-variate;
//                }
                break;
        }
    }

//    private void initViewItem(ImageView imageView, double left, double top, double right, double bottom,double angle, double radius) {
//
//        double left1 = left+ radius*Math.cos(angle);
//        double top1 = top + radius*Math.sin(angle);
//        double right1 = right+ radius*Math.cos(angle);
//        double bottom1 = bottom + radius*Math.sin(angle);
//
////        Log.i("initViewItem2","left:"+radius*Math.cos(angle)+"   top:"+radius*Math.sin(angle)+"   right:"+radius*Math.cos(angle)+"   bottom1:"+radius*Math.sin(angle));
//
////        ViewGroup.MarginLayoutParams margin=new ViewGroup.MarginLayoutParams(imageView.getLayoutParams());
////        margin.setMargins((int)left1,(int)top1, (int)right1,(int)bottom1);
////        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
////        imageView.setLayoutParams(layoutParams);
//
//
//        PropertyValuesHolder a = PropertyValuesHolder.ofFloat("alpha", 1f, 0.8f, 1f);
//        PropertyValuesHolder r = PropertyValuesHolder.ofFloat("rotationY", 0.0f, 360.0f, 0.0f);
//        PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", 0.0f, (float) left1);
//        PropertyValuesHolder y = PropertyValuesHolder.ofFloat("translationY", 0.0f, (float) top1);
//        ofPropertyValuesHolder(imageView,a,x,y).setDuration(2000).start();
//
//        Log.i("initViewItem1","(float)left:"+(float) left+"   (float) top:"+(float) top+"   (float) left1:"+(float) left1+"   (float) top1:"+(float) top1);
////        Log.i("initViewItem1","(int)left1:"+(int)left1+"   (int)top1:"+(int)top1+"   (int)right1:"+(int)right1+"   (int)bottom1:"+(int)bottom1);
//    }
}
