package com.airbnb.android.lib.receivers;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.app.NotificationCompat.Builder;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.analytics.MagicalWifiAnalytics;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.WifiAlarmUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.DLSReservationObjectActivity;
import com.airbnb.android.lib.services.WifiAlarmService;
import com.airbnb.android.utils.AirbnbConstants;

public class WifiAlarmReceiver extends BroadcastReceiver {
    public static String KEY_RESERVATION = "reservation";
    AirbnbPreferences preferences;

    private static long getAlarmEndTime(AirDate checkinDate) {
        return checkinDate.plusDays(1).getTimeInMillisAtStartOfDay();
    }

    public void onReceive(Context context, Intent intent) {
        String str;
        ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
        Bundle bundle = intent.getExtras();
        bundle.setClassLoader(getClass().getClassLoader());
        try {
            Reservation reservation = (Reservation) bundle.getParcelable(KEY_RESERVATION);
            if (reservation == null || reservation.getListing() == null) {
                if (reservation == null) {
                    str = "null_reservation";
                } else {
                    str = "null_listing";
                }
                WifiAlarmUtils.cancelWifiAlarm(context, reservation, str);
                return;
            }
            ListingWirelessInfo info = reservation.getListing().getWirelessInfo();
            WifiManager manager = (WifiManager) context.getSystemService("wifi");
            boolean configAlreadyExists = NetworkUtil.doesNetworkConfigurationAlreadyExist(manager, info);
            if (configAlreadyExists || isAlarmExpired(reservation)) {
                WifiAlarmUtils.cancelWifiAlarm(context, reservation, configAlreadyExists ? "config_already_exists" : "alarm_expired");
            } else if (NetworkUtil.isNetworkAvailable(manager, info, context)) {
                showConnectToWifiNotification(context, reservation);
                WifiAlarmUtils.cancelWifiAlarm(context, reservation, "showed_notification");
            }
        } catch (Exception e) {
            ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(WifiAlarmUtils.pendingIntentForWifiAlarm(context, null));
            MagicalWifiAnalytics.trackOnReceiveError();
        }
    }

    private static boolean isAlarmExpired(Reservation reservation) {
        return System.currentTimeMillis() > getAlarmEndTime(reservation.getCheckinDate());
    }

    private void showConnectToWifiNotification(Context context, Reservation reservation) {
        MagicalWifiAnalytics.trackShowNotification(reservation);
        Builder builder = new Builder(context).setColor(context.getResources().getColor(C0880R.color.c_rausch)).setAutoCancel(true).setDefaults(getNotificationAlerts()).setCategory(NotificationCompat.CATEGORY_MESSAGE).setSmallIcon(C0880R.C0881drawable.ic_stat_notify).setContentText(context.getString(C0880R.string.connect_to_wireless_network)).setContentTitle(context.getString(C0880R.string.detected_wireless_network));
        builder.setContentIntent(PendingIntent.getBroadcast(context, 0, DLSReservationObjectActivity.intentForReservationId(context, reservation.getId()), 134217728));
        builder.addAction(C0880R.C0881drawable.icon_play, context.getString(C0880R.string.connect), PendingIntent.getService(context, 43, WifiAlarmService.intentForWifiConnect(context, reservation.getListing().getWirelessInfo(), reservation), 134217728));
        ((NotificationManager) context.getSystemService("notification")).notify(AirbnbConstants.WIFI_NOTIFICATION_ID, builder.build());
    }

    public int getNotificationAlerts() {
        return 7;
    }
}
