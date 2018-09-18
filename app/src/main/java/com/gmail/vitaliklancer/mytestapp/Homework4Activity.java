package com.gmail.vitaliklancer.mytestapp;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

public class Homework4Activity extends Activity {
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework4);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewAnim);
        imageView.setBackgroundResource(R.drawable.owlanimation);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();
    }
}
