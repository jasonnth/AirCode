package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;

public class GuestInquiryPushNotification extends PushNotification {
    public GuestInquiryPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void receivePushNotification() {
        syncGuestUpcomingTrips(true);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentWithMain(InboxActivityIntents.intentForInbox(this.context, InboxType.Guest));
    }
}
