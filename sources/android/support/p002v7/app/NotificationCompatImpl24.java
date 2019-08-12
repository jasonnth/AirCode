package android.support.p002v7.app;

import android.annotation.TargetApi;
import android.app.Notification.DecoratedCustomViewStyle;
import android.app.Notification.DecoratedMediaCustomViewStyle;
import android.support.p000v4.app.NotificationBuilderWithBuilderAccessor;

@TargetApi(24)
/* renamed from: android.support.v7.app.NotificationCompatImpl24 */
class NotificationCompatImpl24 {
    NotificationCompatImpl24() {
    }

    public static void addDecoratedCustomViewStyle(NotificationBuilderWithBuilderAccessor b) {
        b.getBuilder().setStyle(new DecoratedCustomViewStyle());
    }

    public static void addDecoratedMediaCustomViewStyle(NotificationBuilderWithBuilderAccessor b) {
        b.getBuilder().setStyle(new DecoratedMediaCustomViewStyle());
    }
}
