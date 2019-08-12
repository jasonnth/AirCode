package com.airbnb.android.sharing.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.utils.Strap;

public final class SharingAnalytics extends BaseAnalytics {
    private static final String EVENT_NAME = "sharing";

    public static void trackShareButtonClick(String service, String entryPoint, Strap additionalParams) {
        track("share_button", "click", service, entryPoint, additionalParams);
    }

    public static void trackShareSent(String service, String entryPoint, Strap additionalParams) {
        track("share_result", "success", service, entryPoint, additionalParams);
    }

    public static void trackShareCanceld(String service, String entryPoint, Strap additionalParams) {
        track("share_result", BaseAnalytics.FAILURE, service, entryPoint, additionalParams);
    }

    private static void track(String target, String operation, String service, String entryPoint, Strap additionalParams) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv(BaseAnalytics.TARGET, target).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv("service", service).mo11639kv(ShareActivityIntents.ARG_ENTRY_POINT, entryPoint).mix(additionalParams));
    }
}
