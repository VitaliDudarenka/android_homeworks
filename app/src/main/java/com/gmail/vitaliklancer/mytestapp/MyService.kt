package com.gmail.vitaliklancer.mytestapp

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Binder
import android.os.IBinder
import android.support.v4.content.LocalBroadcastManager


class MyService : Service() {
    private val mBinder = MyBinder()
    private val connectionReceiver = ConnReceiver()
    override fun onBind(intent: Intent?): IBinder? {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION)
        registerReceiver(connectionReceiver, intentFilter)
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        unregisterReceiver(connectionReceiver)
        return super.onUnbind(intent)
    }


    class MyBinder : Binder() {
        fun getService(): MyService {
            return MyService()
        }
    }

    fun turnWifi(activity: Activity) {
        val wifiManager: WifiManager = activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(false);
        } else {
            wifiManager.setWifiEnabled(true);
        }
    }
}

class ConnReceiver : BroadcastReceiver() {
    var message: String = ""


    private var connectivityManager: ConnectivityManager? = null
    override fun onReceive(context: Context, intent: Intent) {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val intent1 = Intent("my-string")
        if (hasConnection()) {
            message = "WIFI ON"
            intent1.putExtra("message", "WIFI ON")
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent1)
        } else {
            message = "WIFI OFF"
            intent1.putExtra("message", "WIFI OFF")
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent1)
        }
    }

    protected fun hasConnection(): Boolean {
        val netInfo = connectivityManager!!.activeNetworkInfo
        if (netInfo != null && netInfo.isConnected) {
            when (netInfo.type) {
                ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_WIMAX -> return true
                else -> return false
            }
        }
        return false
    }


}


