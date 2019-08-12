package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;
import com.airbnb.android.lib.share.ShareYourTripToWechatFragment;

public class ShareYourTripPromptPushNotification extends PushNotification {
    private static final String KEY_TRIP_TITLE = "air_title";
    private final String tripTitleOverride;

    public ShareYourTripPromptPushNotification(Context context, Intent intent) {
        super(context, intent);
        this.tripTitleOverride = intent.getStringExtra(KEY_TRIP_TITLE);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntentsWithMain(HomeActivityIntents.intentForTrips(this.context), ShareYourTripToWechatFragment.newIntentFromPush(this.context, this.serverObjectId, this.tripTitleOverride));
    }
}
