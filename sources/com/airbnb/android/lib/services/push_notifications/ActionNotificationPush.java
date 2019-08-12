package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;

public class ActionNotificationPush extends PushNotification {
    public ActionNotificationPush(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentWithMain(new Intent("android.intent.action.VIEW", Uri.parse(this.intent.getExtras().getString("deeplink_url"))));
    }
}
