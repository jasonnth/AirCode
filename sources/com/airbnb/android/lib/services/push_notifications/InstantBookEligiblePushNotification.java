package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.core.utils.SettingDeepLink;

public class InstantBookEligiblePushNotification extends PushNotification {
    public InstantBookEligiblePushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentWithMain(ManageListingIntents.intentForExistingListingSetting(this.context, this.serverObjectId, SettingDeepLink.InstantBook));
    }
}
