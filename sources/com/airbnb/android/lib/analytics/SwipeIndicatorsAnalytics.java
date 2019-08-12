package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;

public class SwipeIndicatorsAnalytics {
    public static final String EVENT = "swipe_indicators";

    private static Strap makeParams(String page, String operation) {
        return new Strap().mo11639kv("page", page).mo11639kv(BaseAnalytics.OPERATION, operation);
    }

    public static void track(String page, String operation) {
        AirbnbEventLogger.track(EVENT, makeParams(page, operation));
    }
}
