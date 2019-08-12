package com.airbnb.android.core.services;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.text.TextUtils;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.Services;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.api.JPushInterface;

public class JPushBroadcastReceiver extends WakefulBroadcastReceiver {
    private static final String JSON_PAYLOAD = "cn.jpush.android.EXTRA";
    private static final String TAG = JPushBroadcastReceiver.class.getName();

    public void onReceive(Context context, Intent intent) {
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            String playLoad = intent.getStringExtra("cn.jpush.android.EXTRA");
            if (TextUtils.isEmpty(playLoad)) {
                C0715L.m1191e(TAG, "JPush empty push notification received.");
                return;
            }
            Bundle dataBundle = new Bundle();
            try {
                JSONObject jsonPlayLoad = new JSONObject(playLoad);
                Iterator<String> keys = jsonPlayLoad.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    dataBundle.putString(key, jsonPlayLoad.getString(key));
                }
            } catch (JSONException e) {
                C0715L.m1191e(TAG, "JSON parse error " + e);
            }
            WakefulBroadcastReceiver.startWakefulService(context, new Intent(context, Services.push()).putExtras(dataBundle));
            return;
        }
        C0715L.m1189d(TAG, "NOT A MESSAGE " + intent.getAction() + intent.getExtras());
    }
}
