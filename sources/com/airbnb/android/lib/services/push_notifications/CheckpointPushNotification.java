package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;

public class CheckpointPushNotification extends PushNotification {
    public CheckpointPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentWithMain(VerifiedIdActivityIntents.intentForVerifiedId(this.context));
    }
}
