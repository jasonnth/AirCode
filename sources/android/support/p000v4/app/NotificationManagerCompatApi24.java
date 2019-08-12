package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.app.NotificationManager;

@TargetApi(24)
/* renamed from: android.support.v4.app.NotificationManagerCompatApi24 */
class NotificationManagerCompatApi24 {
    public static boolean areNotificationsEnabled(NotificationManager notificationManager) {
        return notificationManager.areNotificationsEnabled();
    }
}
