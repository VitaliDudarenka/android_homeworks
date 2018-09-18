package com.gmail.vitaliklancer.mytestapp

import android.app.Activity

import android.content.IntentFilter
import android.graphics.drawable.AnimationDrawable
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class Homework5Activity : Activity() {
    private var view1: View? = null
    private val vg: ViewGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homework5)
        val imageView = findViewById<View>(R.id.imageViewAnim2) as ImageView
        imageView.setBackgroundResource(R.drawable.owlanimation);
        val animationDrawable = imageView.getBackground() as AnimationDrawable
        animationDrawable.start()
    }


    val wifiReceiver = ConnectionReceiver()
    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(wifiReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(wifiReceiver)
    }

}