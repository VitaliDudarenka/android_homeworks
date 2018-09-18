package com.gmail.vitaliklancer.mytestapp;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

public class MyTestApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
