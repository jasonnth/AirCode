package com.airbnb.android.core.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.identity.ReviewYourAccountWebViewActivity;
import com.airbnb.android.core.services.PushNotificationBuilder;

public class ReviewYourAccountPushNotification extends PushNotification {
    public ReviewYourAccountPushNotification(Context context, Intent intent) {
        super(context, intent);
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        builder.setLaunchIntent(ReviewYourAccountWebViewActivity.intent(this.context, this.context.getString(C0716R.string.review_your_account_url)));
    }
}
