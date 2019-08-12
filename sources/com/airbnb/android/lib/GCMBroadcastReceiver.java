package com.airbnb.android.lib;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.Services;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = GCMBroadcastReceiver.class.getName();

    public void onReceive(Context context, Intent intent) {
        GoogleCloudMessaging push = GoogleCloudMessaging.getInstance(context);
        if (push == null) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException("GCM instance is null!"));
            return;
        }
        if ("gcm".equals(push.getMessageType(intent))) {
            WakefulBroadcastReceiver.startWakefulService(context, new Intent(context, Services.push()).putExtras(intent.getExtras()));
        } else {
            Log.d(TAG, "NOT A MESSAGE");
        }
    }
}
