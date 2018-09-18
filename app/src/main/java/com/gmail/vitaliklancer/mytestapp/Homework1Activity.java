package com.gmail.vitaliklancer.mytestapp;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Homework1Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView1;
    private TextView textView2;

    public void changeText() {
        String textChange = (String) textView1.getText();
        textView1.setText(textView2.getText());
        textView2.setText(textChange);
        ColorDrawable cd1 = (ColorDrawable) textView1.getBackground();
        int colorCode1 = cd1.getColor();
        ColorDrawable cd2 = (ColorDrawable) textView2.getBackground();
        int colorCode2 = cd2.getColor();
        textView1.setBackgroundColor(colorCode2);
        textView2.setBackgroundColor(colorCode1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework1);

        textView1 = (TextView) findViewById(R.id.textView1);

        textView2 = (TextView) findViewById(R.id.textView2);

        Button chngBtn = (Button) findViewById(R.id.button1);

        chngBtn.setOnClickListener(onClickListener);
        textView1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText();
            }
        });
        textView2.setOnClickListener(this);
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeText();

        }
    };

    @Override
    public void onClick(View v) {
        changeText();
    }
}
