package com.airbnb.android.core.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

public class NetworkMonitor {
    private static final String TAG = "NetworkMonitor";
    private final ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public boolean isLowBandwidth;
    private boolean isRoaming;
    private final TelephonyManager telephonyManager;

    public NetworkMonitor(Context context) {
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.isLowBandwidth = getNetworkClass().ordinal() <= NetworkClass.TYPE_2G.ordinal();
        context.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context1, Intent intent) {
                NetworkClass networkClass = NetworkMonitor.this.getNetworkClass();
                Log.i(NetworkMonitor.TAG, "New NetworkClass is " + networkClass.description);
                NetworkMonitor.this.isLowBandwidth = networkClass.ordinal() <= NetworkClass.TYPE_2G.ordinal();
            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    public boolean isRoaming() {
        return this.isRoaming;
    }

    public boolean isLowBandwidth() {
        return this.isLowBandwidth;
    }

    public NetworkClass getNetworkClass() {
        if (this.connectivityManager != null && isWifiNetwork()) {
            this.isRoaming = false;
            return NetworkClass.TYPE_WIFI;
        } else if (this.telephonyManager == null) {
            return NetworkClass.Unknown;
        } else {
            if (this.telephonyManager.isNetworkRoaming()) {
                this.isRoaming = true;
                return NetworkClass.TYPE_ROAMING;
            }
            this.isRoaming = false;
            return getTelephonyNetworkClass();
        }
    }

    private boolean isWifiNetwork() {
        NetworkInfo activeNetwork = this.connectivityManager.getActiveNetworkInfo();
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting() || activeNetwork.getType() != 1) {
            return false;
        }
        return true;
    }

    private NetworkClass getTelephonyNetworkClass() {
        switch (this.telephonyManager.getNetworkType()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 7:
            case 11:
                return NetworkClass.TYPE_2G;
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
                return NetworkClass.TYPE_3G;
            case 13:
            case 15:
                return NetworkClass.TYPE_4G;
            default:
                return NetworkClass.Unknown;
        }
    }
}
