package com.appboy.push;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat.BigTextStyle;
import android.support.p000v4.app.NotificationCompat.Builder;
import android.support.p000v4.app.NotificationCompat.WearableExtender;
import com.appboy.Constants;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;

public class AppboyWearableNotificationUtils {
    private static final String TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, AppboyWearableNotificationUtils.class.getName()});

    public static void setWearableNotificationFeaturesIfPresentAndSupported(Context context, Builder notificationBuilder, Bundle notificationExtras) {
        if (notificationExtras != null) {
            WearableExtender wearableExtender = new WearableExtender();
            if (notificationExtras.containsKey("ab_wi")) {
                wearableExtender.setHintHideIcon(Boolean.valueOf(Boolean.parseBoolean(notificationExtras.getString("ab_wi"))).booleanValue());
            }
            if (notificationExtras.containsKey("ab_wb")) {
                Bitmap wearNotificationBackgroundBitmap = AppboyImageUtils.getBitmap(Uri.parse(notificationExtras.getString("ab_wb")));
                if (wearNotificationBackgroundBitmap != null) {
                    wearableExtender.setBackground(wearNotificationBackgroundBitmap);
                }
            }
            int currentPageIndex = 0;
            while (true) {
                if (!isWearExtraPagePresentInBundle(notificationExtras, currentPageIndex)) {
                    break;
                }
                String extraPageTitle = notificationExtras.getString("ab_we_t" + currentPageIndex);
                String extraPageText = notificationExtras.getString("ab_we_c" + currentPageIndex);
                if (extraPageText == null || extraPageTitle == null) {
                    AppboyLogger.m1733d(TAG, String.format("The title or content of extra page %s was null. Adding no further extra pages.", new Object[]{Integer.valueOf(currentPageIndex)}));
                } else {
                    BigTextStyle extraPageStyle = new BigTextStyle();
                    extraPageStyle.bigText(extraPageText);
                    wearableExtender.addPage(new Builder(context).setContentTitle(extraPageTitle).setStyle(extraPageStyle).build());
                    currentPageIndex++;
                }
            }
            AppboyLogger.m1733d(TAG, String.format("The title or content of extra page %s was null. Adding no further extra pages.", new Object[]{Integer.valueOf(currentPageIndex)}));
            notificationBuilder.extend(wearableExtender);
        }
    }

    private static boolean isWearExtraPagePresentInBundle(Bundle appboyExtras, int pageNumber) {
        return appboyExtras.containsKey(new StringBuilder().append("ab_we_t").append(pageNumber).toString()) && appboyExtras.containsKey(new StringBuilder().append("ab_we_c").append(pageNumber).toString());
    }
}
