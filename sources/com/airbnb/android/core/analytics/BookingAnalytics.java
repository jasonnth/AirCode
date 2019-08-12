package com.airbnb.android.core.analytics;

import com.airbnb.android.lib.analytics.ManageListingAnalytics;
import com.airbnb.android.utils.Strap;

public class BookingAnalytics extends BaseAnalytics {
    public static final String EVENT_NAME = "booking";

    public static Strap getP4NavigationTrackingParams(boolean isP4Redesign) {
        if (isP4Redesign) {
            return Strap.make().mo11639kv(ManageListingAnalytics.FLOW, "HomesBooking");
        }
        return Strap.make();
    }
}
