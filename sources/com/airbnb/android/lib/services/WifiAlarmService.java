package com.airbnb.android.lib.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.widget.Toast;
import com.airbnb.android.core.analytics.MagicalWifiAnalytics;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.NetworkUtil.hostWifiNetworkAddedCallback;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.AirbnbConstants;

public class WifiAlarmService extends IntentService implements hostWifiNetworkAddedCallback {
    private static final String KEY_RESERVATION = "reservation";
    private static final String KEY_WIFI_INFO = "wifi_info";
    public static final String TAG = WifiAlarmService.class.getSimpleName();
    private boolean hasWifiConnectedBroadcastReceiverEverBeenRegistered = false;
    private Reservation reservation;
    private final BroadcastReceiver wifiConnectedBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState() == State.CONNECTED) {
                Toast.makeText(context, C0880R.string.wifi_connected_to_wifi, 0).show();
                context.unregisterReceiver(this);
            }
        }
    };

    public static Intent intentForWifiConnect(Context context, ListingWirelessInfo info, Reservation reservation2) {
        Intent intent = new Intent(context, WifiAlarmService.class);
        intent.putExtra(KEY_WIFI_INFO, info);
        intent.putExtra("reservation", reservation2);
        return intent;
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.wifiConnectedBroadcastReceiver);
        } catch (IllegalArgumentException e) {
        }
    }

    public WifiAlarmService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        ((NotificationManager) getSystemService("notification")).cancel(AirbnbConstants.WIFI_NOTIFICATION_ID);
        ListingWirelessInfo info = (ListingWirelessInfo) intent.getParcelableExtra(KEY_WIFI_INFO);
        this.reservation = (Reservation) intent.getParcelableExtra("reservation");
        if (!this.hasWifiConnectedBroadcastReceiverEverBeenRegistered) {
            this.hasWifiConnectedBroadcastReceiverEverBeenRegistered = NetworkUtil.connectToWifiNetwork(info, getApplicationContext(), this, this.wifiConnectedBroadcastReceiver);
        } else {
            NetworkUtil.connectToWifiNetwork(info, getApplicationContext(), this, null);
        }
    }

    public void onHostWifiNetworkAdded() {
        MagicalWifiAnalytics.trackNetworkAdded(this.reservation);
    }
}
