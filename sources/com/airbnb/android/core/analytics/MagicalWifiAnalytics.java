package com.airbnb.android.core.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.utils.Strap;

public class MagicalWifiAnalytics {
    private static final String ACTION = "action";
    private static final String EVENT = "magical_wifi";

    private static Strap getBaseArgs(String action, Reservation reservation) {
        Strap strap = Strap.make().mo11639kv("action", action);
        if (reservation != null) {
            strap.mo11638kv("reservation_id", reservation.getId());
        }
        return strap;
    }

    public static void trackScheduleWifiAlarm(Reservation reservation) {
        AirbnbEventLogger.track(EVENT, getBaseArgs("schedule_wifi_alarm", reservation));
    }

    public static void trackCancelWifiAlarm(Reservation reservation, String cancelReason) {
        AirbnbEventLogger.track(EVENT, getBaseArgs("cancel_wifi_alarm", reservation).mo11639kv("cancel_reason", cancelReason));
    }

    public static void trackShowNotification(Reservation reservation) {
        AirbnbEventLogger.track(EVENT, getBaseArgs("show_notification", reservation));
    }

    public static void trackNetworkAdded(Reservation reservation) {
        AirbnbEventLogger.track(EVENT, getBaseArgs("network_added", reservation));
    }

    public static void trackOnReceiveError() {
        AirbnbEventLogger.track(EVENT, Strap.make().mo11639kv("action", "unparcel_reservation_error"));
    }
}
