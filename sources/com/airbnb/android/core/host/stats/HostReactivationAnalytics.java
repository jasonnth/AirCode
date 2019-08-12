package com.airbnb.android.core.host.stats;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;

public class HostReactivationAnalytics {
    private static final String EVENT = "host_reactivation";

    public static void trackImpression() {
        AirbnbEventLogger.track(EVENT, Strap.make().mo11639kv("action", "impression"));
    }

    public static void trackReactivateClick() {
        AirbnbEventLogger.track(EVENT, Strap.make().mo11639kv("action", "click_reactivate"));
    }
}
