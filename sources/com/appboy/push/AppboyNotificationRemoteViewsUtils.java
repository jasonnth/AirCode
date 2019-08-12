package com.appboy.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.RemoteViews;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PackageUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppboyNotificationRemoteViewsUtils {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationRemoteViewsUtils.class.getName()});

    @TargetApi(11)
    public static RemoteViews createMultiLineContentNotificationView(Context context, Bundle notificationExtras, int smallIconResourceId, boolean showSmallIcon) {
        int layoutResourceId;
        RemoteViews remoteViews;
        if (notificationExtras != null) {
            String title = notificationExtras.getString("t");
            String contentText = notificationExtras.getString("a");
            Resources resources = context.getResources();
            String resourcePackageName = PackageUtils.getResourcePackageName(context);
            if (showSmallIcon) {
                layoutResourceId = resources.getIdentifier("com_appboy_notification", "layout", resourcePackageName);
            } else {
                layoutResourceId = resources.getIdentifier("com_appboy_notification_no_icon", "layout", resourcePackageName);
            }
            int titleResourceId = resources.getIdentifier("com_appboy_notification_title", "id", resourcePackageName);
            int contentResourceId = resources.getIdentifier("com_appboy_notification_content", "id", resourcePackageName);
            int iconResourceId = resources.getIdentifier("com_appboy_notification_icon", "id", resourcePackageName);
            int timeViewResourceId = resources.getIdentifier("com_appboy_notification_time", "id", resourcePackageName);
            int twentyFourHourFormatResourceId = resources.getIdentifier("com_appboy_notification_time_twenty_four_hour_format", "string", resourcePackageName);
            int twelveHourFormatResourceId = resources.getIdentifier("com_appboy_notification_time_twelve_hour_format", "string", resourcePackageName);
            String twentyFourHourTimeFormat = AppboyNotificationUtils.getOptionalStringResource(resources, twentyFourHourFormatResourceId, "HH:mm");
            String twelveHourTimeFormat = AppboyNotificationUtils.getOptionalStringResource(resources, twelveHourFormatResourceId, "h:mm a");
            if (layoutResourceId == 0 || titleResourceId == 0 || contentResourceId == 0 || iconResourceId == 0 || timeViewResourceId == 0) {
                AppboyLogger.m1739w(TAG, String.format("Couldn't find all resource IDs for custom notification view, extended view will not be used for push notifications. Received %d for layout, %d for title, %d for content, %d for icon, and %d for time.", new Object[]{Integer.valueOf(layoutResourceId), Integer.valueOf(titleResourceId), Integer.valueOf(contentResourceId), Integer.valueOf(iconResourceId), Integer.valueOf(timeViewResourceId)}));
            } else {
                AppboyLogger.m1733d(TAG, "Using RemoteViews for rendering of push notification.");
                try {
                    remoteViews = new RemoteViews(PackageUtils.getResourcePackageName(context), layoutResourceId);
                } catch (Exception e) {
                    AppboyLogger.m1736e(TAG, String.format("Failed to initialize remote views with package %s", new Object[]{PackageUtils.getResourcePackageName(context)}), e);
                    try {
                        remoteViews = new RemoteViews(context.getPackageName(), layoutResourceId);
                    } catch (Exception e2) {
                        AppboyLogger.m1736e(TAG, String.format("Failed to initialize remote views with package %s", new Object[]{context.getPackageName()}), e2);
                        return null;
                    }
                }
                remoteViews.setTextViewText(titleResourceId, title);
                remoteViews.setTextViewText(contentResourceId, contentText);
                if (showSmallIcon) {
                    remoteViews.setImageViewResource(iconResourceId, smallIconResourceId);
                }
                if (!DateFormat.is24HourFormat(context)) {
                    twentyFourHourTimeFormat = twelveHourTimeFormat;
                }
                remoteViews.setTextViewText(timeViewResourceId, new SimpleDateFormat(twentyFourHourTimeFormat).format(new Date()));
                return remoteViews;
            }
        }
        return null;
    }
}
