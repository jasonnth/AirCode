package com.airbnb.android.core.localpushnotifications;

import android.content.Intent;
import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager.PushType;
import com.airbnb.android.core.notifications.PushNotificationConstants;
import com.airbnb.android.utils.Strap;

public final class LocalPushAnalytics extends BaseAnalytics {
    private static final String EVENT_NAME = "android_local_push";

    public static void trackScheduling(PushType pushType, long listingId, String listingName, String localizedCity) {
        String name;
        String str = EVENT_NAME;
        Strap kv = Strap.make().mo11639kv(BaseAnalytics.OPERATION, "schedule_local_push").mo11638kv("listing_id", listingId).mo11639kv("listingName", listingName).mo11639kv("city", localizedCity);
        String str2 = LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE;
        if (pushType == null) {
            name = "";
        } else {
            name = pushType.name();
        }
        AirbnbEventLogger.track(str, kv.mo11639kv(str2, name));
    }

    public static void trackConstructPushIntent(PushType pushType, long listingId, String listingName, String localizedCity) {
        String name;
        String str = EVENT_NAME;
        Strap kv = Strap.make().mo11639kv(BaseAnalytics.OPERATION, "construct_local_push_intent").mo11638kv("listing_id", listingId).mo11639kv("listingName", listingName).mo11639kv("city", localizedCity);
        String str2 = LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE;
        if (pushType == null) {
            name = "";
        } else {
            name = pushType.name();
        }
        AirbnbEventLogger.track(str, kv.mo11639kv(str2, name));
    }

    public static void trackOnRequestError(PushType pushType, String errorMessage) {
        String name;
        String str = EVENT_NAME;
        Strap kv = Strap.make().mo11639kv(BaseAnalytics.OPERATION, "on_request_error").mo11639kv("error_message", errorMessage);
        String str2 = LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE;
        if (pushType == null) {
            name = "";
        } else {
            name = pushType.name();
        }
        AirbnbEventLogger.track(str, kv.mo11639kv(str2, name));
    }

    public static void trackDeliverLocalPushNotification(PushType pushType) {
        String name;
        String str = EVENT_NAME;
        Strap kv = Strap.make().mo11639kv(BaseAnalytics.OPERATION, "deliver_local_push");
        String str2 = LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE;
        if (pushType == null) {
            name = "";
        } else {
            name = pushType.name();
        }
        AirbnbEventLogger.track(str, kv.mo11639kv(str2, name));
    }

    public static void trackListingIsBookedBeforePushDeliverying(PushType pushType) {
        String name;
        String str = EVENT_NAME;
        Strap kv = Strap.make().mo11639kv(BaseAnalytics.OPERATION, "listing_booked_before_push_delivery");
        String str2 = LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE;
        if (pushType == null) {
            name = "";
        } else {
            name = pushType.name();
        }
        AirbnbEventLogger.track(str, kv.mo11639kv(str2, name));
    }

    public static void trackConstructingPushCopy(boolean isSuccess, PushType pushType) {
        String name;
        String str = EVENT_NAME;
        Strap kv = Strap.make().mo11639kv(BaseAnalytics.OPERATION, "constrct_local_push_copy").mo11640kv("is_success", isSuccess);
        String str2 = LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE;
        if (pushType == null) {
            name = "";
        } else {
            name = pushType.name();
        }
        AirbnbEventLogger.track(str, kv.mo11639kv(str2, name));
    }

    public static void trackUserDisabledPushNotificationFromSystem() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "push_disabled_from_system"));
    }

    public static void trackUserHasSeenLocalPush() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "user_has_seen_local_push"));
    }

    public static void trackLocalPushOpen(Intent intent) {
        track("local_push_open", intent);
    }

    private static void track(String operation, Intent intent) {
        if (intent != null && !TextUtils.isEmpty(operation)) {
            PushType pushType = (PushType) intent.getSerializableExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE);
            if (pushType != null) {
                String listingName = intent.getStringExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LISTING_NAME);
                AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv("deep_link", intent.getStringExtra(PushNotificationConstants.DEEP_LINK_KEY)).mo11639kv("listingName", listingName).mo11639kv("city", intent.getStringExtra(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_LOCALIZED_CITY)).mo11639kv(LocalPushNotificationManager.LOCAL_PUSH_EXTRA_PUSH_TYPE, pushType.name()));
            }
        }
    }
}
