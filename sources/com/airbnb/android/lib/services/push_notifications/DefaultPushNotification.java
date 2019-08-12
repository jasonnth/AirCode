package com.airbnb.android.lib.services.push_notifications;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.services.push_notifications.PushNotification;

public class DefaultPushNotification extends PushNotification {
    private final String deepLinkUrl = getDeepLinkUrl();
    private final Bundle pushData;

    public DefaultPushNotification(Context context, Intent intent) {
        super(context, intent);
        this.pushData = intent.getExtras();
    }

    /* access modifiers changed from: protected */
    public void receivePushNotification() {
        if (!TextUtils.isEmpty(this.deepLinkUrl)) {
            PushAnalytics.trackDeepLink(this.deepLinkUrl);
        }
    }

    /* access modifiers changed from: protected */
    public void buildNotification(PushNotificationBuilder builder) {
        if (TextUtils.isEmpty(this.deepLinkUrl)) {
            builder.setLaunchIntent(HomeActivityIntents.intentForDefaultTab(this.context).putExtras(this.pushData));
        } else {
            builder.setLaunchIntentWithMain(new Intent("android.intent.action.VIEW", Uri.parse(this.deepLinkUrl)).putExtras(this.pushData));
        }
    }

    private String getDeepLinkUrl() {
        String deepLinkUrl2 = "";
        if (!this.intent.hasExtra(PushNotificationConstants.DEEP_LINK_KEY)) {
            return deepLinkUrl2;
        }
        String deepLinkUrl3 = this.intent.getStringExtra(PushNotificationConstants.DEEP_LINK_KEY);
        if (!deepLinkUrl3.startsWith("airbnb://")) {
            return "airbnb://d/" + deepLinkUrl3;
        }
        return deepLinkUrl3;
    }

    /* access modifiers changed from: protected */
    public boolean isHandled() {
        return !TextUtils.isEmpty(this.deepLinkUrl);
    }
}
