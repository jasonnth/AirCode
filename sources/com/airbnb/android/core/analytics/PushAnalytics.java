package com.airbnb.android.core.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.requests.PushNotificationConversionRequest;
import com.airbnb.android.utils.Strap;

public class PushAnalytics extends BaseAnalytics {
    public static final String PUSH_NOTIFICATION_EVENT_NAME = "push_notification";

    public static void trackDeepLink(String deepLinkUrl) {
        track(Strap.make().mo11639kv("deep_link_handled", deepLinkUrl));
    }

    public static void trackOverridden() {
        track(Strap.make().mo11639kv(BaseAnalytics.OPERATION, "push_overridden"));
    }

    public static void trackOperation(String type, String id, String operation) {
        track(Strap.make().mo11639kv(PushNotificationConversionRequest.PUSH_TYPE, type).mo11639kv("push_id", id).mo11639kv(BaseAnalytics.OPERATION, operation));
    }

    public static void reportUserUnsubscribed() {
        track(Strap.make().mo11639kv(BaseAnalytics.OPERATION, "unsubscribe"));
    }

    private static void track(Strap strap) {
        AirbnbEventLogger.track(PUSH_NOTIFICATION_EVENT_NAME, strap);
    }
}
