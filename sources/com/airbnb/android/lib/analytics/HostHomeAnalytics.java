package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.utils.Strap;

public class HostHomeAnalytics {
    public static final String HOSPITALITY_ALERTS = "hospitality_alerts";
    public static final String HOST_HOME_EVENT = "host_home";
    public static final String IMPRESSION = "impressions";
    public static final String PRIORITY_ITEM = "tap_priority_item";
    public static final String SET_PAYOUT = "set_payout";

    private static Strap makeStrap(String operation, String section) {
        return new Strap().mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv(BaseAnalytics.SECTION, section);
    }

    public static void trackImpression(int alerts, int reservations) {
        AirbnbEventLogger.track(HOST_HOME_EVENT, makeStrap("impressions", ROAnalytics.GENERAL).mo11637kv("number_of_priority_items", alerts).mo11637kv("number_of_confirmed_reservations", reservations));
    }

    public static void trackPriorityItem(String section, String itemDetails) {
        trackPriorityItemWithId(section, itemDetails, -1);
    }

    public static void trackPriorityItemWithId(String section, String itemDetails, long reservationId) {
        Strap strap = makeStrap(PRIORITY_ITEM, section).mo11639kv("priority_item_type", itemDetails);
        if (reservationId > -1) {
            strap.mo11638kv("reservation_id", reservationId);
        }
        AirbnbEventLogger.track(HOST_HOME_EVENT, strap);
    }

    public static void trackClickSuspensionAlert() {
        AirbnbEventLogger.track(HOST_HOME_EVENT, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "click_suspension_notice"));
    }
}
