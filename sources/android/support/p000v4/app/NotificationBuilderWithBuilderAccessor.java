package android.support.p000v4.app;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;

@TargetApi(11)
/* renamed from: android.support.v4.app.NotificationBuilderWithBuilderAccessor */
public interface NotificationBuilderWithBuilderAccessor {
    Notification build();

    Builder getBuilder();
}
