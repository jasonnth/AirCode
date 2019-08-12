package com.airbnb.android.superhero;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;

public class SuperHeroPushNotification extends PushNotification {
    public SuperHeroPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        if (isHandled()) {
            builder.setLaunchIntent(HomeActivityIntents.intentForSuperHero(this.context, this.serverObjectId));
        }
    }

    /* access modifiers changed from: protected */
    public boolean isHandled() {
        return this.serverObjectId != -1;
    }
}
