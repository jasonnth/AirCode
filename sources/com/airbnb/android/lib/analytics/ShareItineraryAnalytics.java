package com.airbnb.android.lib.analytics;

import android.content.Context;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.utils.Strap;

public class ShareItineraryAnalytics {
    private static final String ANALYTICS_REGISTRY_CONF_CODE = "share_itinerary_confirmation_code";
    private static final String ANALYTICS_REGISTRY_KEY = "share_itinerary_source";
    private static final String EVENT_NAME = "share_itinerary";

    public static void setSource(Context context, String source, String confirmationCode) {
        AirbnbApplication.instance(context).component().analyticsRegistry().getRegistry().mo11639kv(ANALYTICS_REGISTRY_KEY, source).mo11639kv(ANALYTICS_REGISTRY_CONF_CODE, confirmationCode);
    }

    private static String getSource() {
        return (String) AirbnbApplication.instance().component().analyticsRegistry().getRegistry().get(ANALYTICS_REGISTRY_KEY);
    }

    private static String getConfirmationCode() {
        return (String) AirbnbApplication.instance().component().analyticsRegistry().getRegistry().get(ANALYTICS_REGISTRY_CONF_CODE);
    }

    public static void trackSkip() {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", "invite_guests").mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, "skip_button").mo11639kv("referrer", getSource()).mo11639kv("confirmation_code", getConfirmationCode()));
    }

    public static void trackSend(int count, boolean includePrice, boolean customMessage) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", "invite_guests").mo11639kv(BaseAnalytics.OPERATION, "click").mo11639kv(BaseAnalytics.TARGET, "send_button").mo11639kv("referrer", getSource()).mo11639kv("confirmation_code", getConfirmationCode()).mo11637kv("num_email_addresses", count).mo11640kv("include_price", includePrice).mo11640kv("custom_message", customMessage));
    }
}
