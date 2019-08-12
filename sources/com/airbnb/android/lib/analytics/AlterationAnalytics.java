package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationAlteration;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.utils.Strap;

public class AlterationAnalytics {
    public static final String ALTERATION_EVENT = "android_reservation_alteration";
    private static final String PARAM_ALTERATION_ID = "alteration_id";
    private static final String PARAM_CHECKIN_DIFFERENCE = "checkin_difference";
    private static final String PARAM_CHECKOUT_DIFFERENCE = "checkout_difference";
    private static final String PARAM_GUEST_DIFFERENCE = "guest_count_difference";
    private static final String PARAM_INITIATED_BY = "initiated_by";
    private static final String PARAM_INTENT = "intent";
    private static final String PARAM_LAUNCH_ORIGIN = "launch_origin";
    private static final String PARAM_RESERVATION_ID = "reservation_id";
    private static final String PARAM_USER_TYPE = "user_type";
    private static final String VALUE_GUEST = "guest";
    private static final String VALUE_HOST = "host";
    private static final String VALUE_INTENT_CREATE = "create_alteration";
    private static final String VALUE_INTENT_REVIEW = "review_alteration";
    private static final String VALUE_SOURCE_HOST_HOME = "source_host_home";
    private static final String VALUE_SOURCE_ITINERARY = "source_itinerary";

    private static void track(Strap strap) {
        AirbnbEventLogger.track(ALTERATION_EVENT, strap);
    }

    private static Strap makeParams(Reservation reservation, ReservationAlteration alteration) {
        Strap strap = Strap.make();
        strap.mo11638kv("reservation_id", reservation.getId()).mo11639kv(PARAM_USER_TYPE, reservation.isUserHost(AirbnbApplication.instance().component().accountManager().getCurrentUser()) ? "host" : "guest");
        if (alteration != null) {
            strap.mo11638kv(PARAM_ALTERATION_ID, alteration.getId()).mo11637kv(PARAM_GUEST_DIFFERENCE, getGuestDifference(reservation, alteration)).mo11637kv(PARAM_CHECKIN_DIFFERENCE, getCheckinDifference(reservation, alteration)).mo11637kv(PARAM_CHECKOUT_DIFFERENCE, getCheckoutDifference(reservation, alteration)).mo11639kv(PARAM_INITIATED_BY, alteration.isInitiatedByGuest() ? "guest" : "host");
        }
        return strap;
    }

    static void trackAlterReservationFromItinerary(Reservation reservation) {
        track(makeParams(reservation, null).mo11639kv(PARAM_INTENT, VALUE_INTENT_CREATE).mo11639kv(PARAM_LAUNCH_ORIGIN, VALUE_SOURCE_ITINERARY));
    }

    static void trackViewPendingAlterationFromItinerary(Reservation reservation, ReservationAlteration alteration) {
        track(makeParams(reservation, alteration).mo11639kv(PARAM_INTENT, VALUE_INTENT_REVIEW).mo11639kv(PARAM_LAUNCH_ORIGIN, VALUE_SOURCE_ITINERARY));
    }

    public static void trackViewPendingAlterationFromHostAlert(Reservation reservation, ReservationAlteration alteration) {
        track(makeParams(reservation, alteration).mo11639kv(PARAM_INTENT, VALUE_INTENT_REVIEW).mo11639kv(PARAM_LAUNCH_ORIGIN, VALUE_SOURCE_HOST_HOME));
    }

    private static int getGuestDifference(Reservation reservation, ReservationAlteration alteration) {
        return alteration.getGuests() - reservation.getGuestCount();
    }

    private static int getCheckinDifference(Reservation reservation, ReservationAlteration alteration) {
        return Math.abs(reservation.getCheckinDate().getDaysUntil(alteration.getCheckIn()));
    }

    private static int getCheckoutDifference(Reservation reservation, ReservationAlteration alteration) {
        return reservation.getCheckoutDate().getDaysUntil(alteration.getCheckOut());
    }
}
