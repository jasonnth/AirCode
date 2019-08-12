package com.appboy.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat.BigPictureStyle;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Style;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.Constants;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;

public class AppboyNotificationStyleFactory {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyNotificationStyleFactory.class.getName()});

    @TargetApi(16)
    public static Style getBigNotificationStyle(Context context, Bundle notificationExtras, Bundle appboyExtras) {
        Style style = null;
        if (appboyExtras != null && appboyExtras.containsKey("appboy_image_url")) {
            style = getBigPictureNotificationStyle(context, notificationExtras, appboyExtras);
        }
        if (style != null) {
            return style;
        }
        AppboyLogger.m1733d(TAG, "Rendering push notification with BigTextStyle");
        return getBigTextNotificationStyle(notificationExtras);
    }

    public static BigTextStyle getBigTextNotificationStyle(Bundle notificationExtras) {
        if (notificationExtras == null) {
            return null;
        }
        BigTextStyle bigTextNotificationStyle = new BigTextStyle();
        bigTextNotificationStyle.bigText(notificationExtras.getString("a"));
        String bigSummary = null;
        String bigTitle = null;
        if (notificationExtras.containsKey("ab_bs")) {
            bigSummary = notificationExtras.getString("ab_bs");
        }
        if (notificationExtras.containsKey("ab_bt")) {
            bigTitle = notificationExtras.getString("ab_bt");
        }
        if (bigSummary != null) {
            bigTextNotificationStyle.setSummaryText(bigSummary);
        }
        if (bigTitle == null) {
            return bigTextNotificationStyle;
        }
        bigTextNotificationStyle.setBigContentTitle(bigTitle);
        return bigTextNotificationStyle;
    }

    @TargetApi(16)
    public static BigPictureStyle getBigPictureNotificationStyle(Context context, Bundle notificationExtras, Bundle appboyExtras) {
        if (appboyExtras == null || !appboyExtras.containsKey("appboy_image_url")) {
            return null;
        }
        String imageUrl = appboyExtras.getString("appboy_image_url");
        if (StringUtils.isNullOrBlank(imageUrl)) {
            return null;
        }
        Bitmap imageBitmap = AppboyImageUtils.getBitmap(Uri.parse(imageUrl));
        if (imageBitmap == null) {
            return null;
        }
        try {
            if (imageBitmap.getWidth() > imageBitmap.getHeight()) {
                WindowManager windowManager = (WindowManager) context.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                int bigPictureHeightPixels = AppboyImageUtils.getPixelsFromDensityAndDp(displayMetrics.densityDpi, 192);
                int bigPictureWidthPixels = bigPictureHeightPixels * 2;
                if (bigPictureWidthPixels > displayMetrics.widthPixels) {
                    bigPictureWidthPixels = displayMetrics.widthPixels;
                }
                try {
                    imageBitmap = Bitmap.createScaledBitmap(imageBitmap, bigPictureWidthPixels, bigPictureHeightPixels, true);
                } catch (Exception e) {
                    AppboyLogger.m1736e(TAG, "Failed to scale image bitmap, using original.", e);
                }
            }
            if (imageBitmap == null) {
                AppboyLogger.m1737i(TAG, "Bitmap download failed for push notification. No image will be included with the notification.");
                return null;
            }
            BigPictureStyle bigPictureNotificationStyle = new BigPictureStyle();
            bigPictureNotificationStyle.bigPicture(imageBitmap);
            setBigPictureSummaryAndTitle(bigPictureNotificationStyle, notificationExtras);
            return bigPictureNotificationStyle;
        } catch (Exception e2) {
            AppboyLogger.m1736e(TAG, "Failed to create Big Picture Style.", e2);
            return null;
        }
    }

    static void setBigPictureSummaryAndTitle(BigPictureStyle bigPictureNotificationStyle, Bundle notificationExtras) {
        String bigSummary = null;
        String bigTitle = null;
        if (notificationExtras.containsKey("ab_bs")) {
            bigSummary = notificationExtras.getString("ab_bs");
        }
        if (notificationExtras.containsKey("ab_bt")) {
            bigTitle = notificationExtras.getString("ab_bt");
        }
        if (bigSummary != null) {
            bigPictureNotificationStyle.setSummaryText(bigSummary);
        }
        if (bigTitle != null) {
            bigPictureNotificationStyle.setBigContentTitle(bigTitle);
        }
        if (notificationExtras.getString("s") == null && bigSummary == null) {
            bigPictureNotificationStyle.setSummaryText(notificationExtras.getString("a"));
        }
    }
}
