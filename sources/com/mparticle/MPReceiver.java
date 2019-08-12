package com.mparticle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.messaging.AbstractCloudMessage;
import com.mparticle.messaging.CloudAction;
import com.mparticle.messaging.MPMessagingAPI;

public class MPReceiver extends BroadcastReceiver {
    public static final String MPARTICLE_IGNORE = "mparticle_ignore";

    public MPReceiver() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                try {
                    Class.forName("android.os.AsyncTask");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public final void onReceive(Context context, Intent intent) {
        if (!MPARTICLE_IGNORE.equals(intent.getAction()) && !intent.getBooleanExtra(MPARTICLE_IGNORE, false)) {
            if ("com.android.vending.INSTALL_REFERRER".equals(intent.getAction())) {
                context.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0).edit().putString("mp::install_referrer", intent.getStringExtra("referrer")).apply();
            } else if (MPMessagingAPI.BROADCAST_NOTIFICATION_TAPPED.equalsIgnoreCase(intent.getAction())) {
                if (!onNotificationTapped((AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA), (CloudAction) intent.getParcelableExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA))) {
                    MPService.runIntentInService(context, intent);
                }
            } else if (!MPMessagingAPI.BROADCAST_NOTIFICATION_RECEIVED.equalsIgnoreCase(intent.getAction())) {
                MPService.runIntentInService(context, intent);
            } else if (!onNotificationReceived((AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA))) {
                MPService.runIntentInService(context, intent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onNotificationReceived(AbstractCloudMessage message) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onNotificationTapped(AbstractCloudMessage message, CloudAction action) {
        return false;
    }
}
