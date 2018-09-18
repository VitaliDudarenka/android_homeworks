package com.gmail.vitaliklancer.mytestapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.gmail.vitaliklancer.mytestapp.Homework7.HW7MainActivity;
import com.squareup.leakcanary.LeakCanary;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.buttonHW1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homework1Activity.class);
                startActivity(intent);
            }
        });
        Button button2 = findViewById(R.id.buttonHW2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homework2Activity.class);
                startActivity(intent);
            }
        });
        Button button3 = findViewById(R.id.buttonHW3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homework3Activity.class);
                startActivity(intent);
            }
        });
        Button button4 = findViewById(R.id.buttonHW4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homework4Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.diagonaltranslate, R.anim.alpha);
            }
        });
        Button button5 = findViewById(R.id.buttonHW5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homework5Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.diagonaltranslate, R.anim.alpha);
            }
        });
        Button button6 = findViewById(R.id.buttonHW6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Homework6Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.diagonaltranslate, R.anim.alpha);
            }
        });
        Button button7 = findViewById(R.id.buttonHW7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HW7MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.diagonaltranslate, R.anim.alpha);
            }
        });
    }
}

