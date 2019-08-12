package com.mparticle.messaging;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PushAnalyticsReceiver extends BroadcastReceiver implements PushAnalyticsReceiverCallback {
    public final void onReceive(Context context, Intent intent) {
        MPMessagingRouter.onReceive(context, intent, this);
    }

    public boolean onNotificationReceived(AbstractCloudMessage message) {
        return false;
    }

    public boolean onNotificationTapped(AbstractCloudMessage message, CloudAction action) {
        return false;
    }
}
