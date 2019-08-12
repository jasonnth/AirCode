package com.airbnb.android.core;

import android.content.BroadcastReceiver;

public final class BroadcastReceivers extends ClassRegistry {
    private BroadcastReceivers() {
    }

    private static Class<? extends BroadcastReceiver> maybeLoadBroadcastReceiverClass(String className) {
        return maybeLoadClass(className);
    }

    public static Class<? extends BroadcastReceiver> localPushNotification() {
        return maybeLoadBroadcastReceiverClass("com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager");
    }

    public static Class<? extends BroadcastReceiver> wifiAlarm() {
        return maybeLoadBroadcastReceiverClass("com.airbnb.android.lib.receivers.WifiAlarmReceiver");
    }
}
