package com.gmail.vitaliklancer.mytestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {

private ConnectivityManager connectivityManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (hasConnection()) {
            Toast.makeText(context, "The connection is established", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "The connection was interrupted", Toast.LENGTH_LONG).show();
        }
    }

    protected boolean hasConnection() {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            switch (netInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                case ConnectivityManager.TYPE_WIFI:
                case ConnectivityManager.TYPE_WIMAX:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
}
