package com.appboy.push;

import android.app.Notification;
import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import com.appboy.IAppboyNotificationFactory;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.ui.R;

public class AppboyNotificationFactory implements IAppboyNotificationFactory {
    private static volatile AppboyNotificationFactory sInstance = null;

    public static AppboyNotificationFactory getInstance() {
        if (sInstance == null) {
            synchronized (AppboyNotificationFactory.class) {
                if (sInstance == null) {
                    sInstance = new AppboyNotificationFactory();
                }
            }
        }
        return sInstance;
    }

    public Notification createNotification(AppboyConfigurationProvider appConfigurationProvider, Context context, Bundle notificationExtras, Bundle appboyExtras) {
        boolean z = true;
        Builder notificationBuilder = new Builder(context).setAutoCancel(true);
        AppboyNotificationUtils.setTitleIfPresent(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setContentIfPresent(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setTickerIfPresent(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setContentIntentIfPresent(context, notificationBuilder, notificationExtras);
        int smallNotificationIconResourceId = AppboyNotificationUtils.setSmallIcon(appConfigurationProvider, notificationBuilder);
        boolean usingLargeIcon = AppboyNotificationUtils.setLargeIconIfPresentAndSupported(context, appConfigurationProvider, notificationBuilder, notificationExtras);
        notificationBuilder.setSound(getRawResourceUri(R.raw.push_notification), 5);
        notificationBuilder.setDefaults(6);
        if (VERSION.SDK_INT >= 11 && VERSION.SDK_INT < 16) {
            if (usingLargeIcon) {
                z = false;
            }
            RemoteViews remoteViews = AppboyNotificationRemoteViewsUtils.createMultiLineContentNotificationView(context, notificationExtras, smallNotificationIconResourceId, z);
            if (remoteViews != null) {
                notificationBuilder.setContent(remoteViews);
                return notificationBuilder.build();
            }
        }
        AppboyNotificationUtils.setSummaryTextIfPresentAndSupported(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setPriorityIfPresentAndSupported(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setStyleIfSupported(context, notificationBuilder, notificationExtras, appboyExtras);
        AppboyNotificationActionUtils.addNotificationActions(context, notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setAccentColorIfPresentAndSupported(appConfigurationProvider, notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setCategoryIfPresentAndSupported(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setVisibilityIfPresentAndSupported(notificationBuilder, notificationExtras);
        AppboyNotificationUtils.setPublicVersionIfPresentAndSupported(context, appConfigurationProvider, notificationBuilder, notificationExtras);
        AppboyWearableNotificationUtils.setWearableNotificationFeaturesIfPresentAndSupported(context, notificationBuilder, notificationExtras);
        return notificationBuilder.build();
    }

    public static Uri getRawResourceUri(int rawResourceId) {
        return Uri.parse("android.resource://com.appboy.ui/" + rawResourceId);
    }
}
