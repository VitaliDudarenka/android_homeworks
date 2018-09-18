package com.gmail.vitaliklancer.mytestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Homework3Activity extends Activity {
    private EditText editText;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_homework3);
        imageView = findViewById(R.id.imageView);
        editText = findViewById(R.id.editTextImageUrl);
        Button button = findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.get().load(String.valueOf(editText.getText())).transform(new CropCircleTransformation()).
                        placeholder(R.drawable.progress_animation).into(imageView);
            }
        });


    }
}
