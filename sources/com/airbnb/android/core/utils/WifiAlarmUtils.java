package com.airbnb.android.core.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.p000v4.app.NotificationCompat;
import com.airbnb.android.core.BroadcastReceivers;
import com.airbnb.android.core.analytics.MagicalWifiAnalytics;
import com.airbnb.android.core.models.Reservation;

public final class WifiAlarmUtils {
    private static final long CHECKING_INTERVAL_MILLIS = 1800000;
    private static final String KEY_RESERVATION = "reservation";
    private static final int REQUEST_CODE_WIFI_ALARM = 42;
    public static final int REQUEST_CODE_WIFI_CONNECT = 43;

    private WifiAlarmUtils() {
    }

    public static PendingIntent pendingIntentForWifiAlarm(Context context, Reservation reservation) {
        return PendingIntent.getBroadcast(context, 42, new Intent(context, BroadcastReceivers.wifiAlarm()).putExtra("reservation", reservation), 134217728);
    }

    public static void scheduleWifiAlarm(Context context, Reservation reservation) {
        MagicalWifiAnalytics.trackScheduleWifiAlarm(reservation);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(1, reservation.getCheckinDate().getTimeInMillisAtStartOfDay(), CHECKING_INTERVAL_MILLIS, pendingIntentForWifiAlarm(context, reservation));
    }

    public static void cancelWifiAlarm(Context context, Reservation reservation, String cancelReason) {
        MagicalWifiAnalytics.trackCancelWifiAlarm(reservation, cancelReason);
        ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(pendingIntentForWifiAlarm(context, null));
    }
}
