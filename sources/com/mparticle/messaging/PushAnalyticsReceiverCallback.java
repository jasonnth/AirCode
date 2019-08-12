package com.mparticle.messaging;

public interface PushAnalyticsReceiverCallback {
    boolean onNotificationReceived(AbstractCloudMessage abstractCloudMessage);

    boolean onNotificationTapped(AbstractCloudMessage abstractCloudMessage, CloudAction cloudAction);
}
