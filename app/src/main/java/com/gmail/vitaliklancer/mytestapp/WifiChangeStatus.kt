package com.gmail.vitaliklancer.mytestapp

import android.content.Context
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import java.lang.reflect.Method

class WifiChangeStatus {
    /*fun isApOn(context: Context): Boolean {
        val wifimanager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        try {
            val method: Method = wifimanager.javaClass.getDeclaredMethod("isWifiApEnabled")
            method.isAccessible = true
            return method.invoke(wifimanager) as Boolean
        } catch (ignored: Throwable) {
        }

        return false
    }

    fun configApState(context: Context): Boolean {
        val wifimanager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wificonfiguration: WifiConfiguration? = null
        try {
            if (isApOn(context)) {
                wifimanager.setWifiEnabled(false)
            }
            val method: Method = wifimanager.javaClass.getMethod("setWifiApEnabled", WifiConfiguration::class.java, Boolean::class.javaPrimitiveType)
            method.invoke(wifimanager, wificonfiguration, !isApOn(context))
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }*/



}