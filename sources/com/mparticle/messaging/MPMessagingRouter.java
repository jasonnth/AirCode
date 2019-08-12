package com.mparticle.messaging;

import android.content.Context;
import android.content.Intent;
import com.mparticle.MPService;

public class MPMessagingRouter {
    public static boolean onReceive(Context context, Intent intent, PushAnalyticsReceiverCallback callback) {
        if (MPMessagingAPI.BROADCAST_NOTIFICATION_TAPPED.equalsIgnoreCase(intent.getAction())) {
            AbstractCloudMessage abstractCloudMessage = (AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA);
            CloudAction cloudAction = (CloudAction) intent.getParcelableExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA);
            if (!callback.onNotificationTapped(abstractCloudMessage, cloudAction)) {
                intent.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, abstractCloudMessage);
                intent.putExtra(MPMessagingAPI.CLOUD_ACTION_EXTRA, cloudAction);
                MPService.runIntentInService(context, intent);
            }
            return true;
        } else if (!MPMessagingAPI.BROADCAST_NOTIFICATION_RECEIVED.equalsIgnoreCase(intent.getAction())) {
            return false;
        } else {
            AbstractCloudMessage abstractCloudMessage2 = (AbstractCloudMessage) intent.getParcelableExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA);
            if (!callback.onNotificationReceived(abstractCloudMessage2)) {
                intent.putExtra(MPMessagingAPI.CLOUD_MESSAGE_EXTRA, abstractCloudMessage2);
                MPService.runIntentInService(context, intent);
            }
            return true;
        }
    }
}
