package com.airbnb.android.lib.analytics;

import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.utils.Strap;

public class ROAnalytics {
    public static final String GENERAL = "general";
    public static final String RESERVATION_REQUEST = "reservation_request";
    public static final String RESPOND_NOW = "respond_now";

    /* renamed from: RO */
    public static final String f9493RO = "reservation_object";
    public static final String RO_ITINERARY = "ro_itinerary";

    private static Strap makeParams(String page, String operation, String section, long reservationId, Reservation reservation, long threadId) {
        Strap strap = Strap.make().mo11639kv("page", page).mo11639kv(BaseAnalytics.OPERATION, operation).mo11639kv(BaseAnalytics.SECTION, section);
        if (reservationId > 0) {
            strap.mo11638kv("reservation_id", reservationId);
        }
        if (threadId > 0) {
            strap.mo11638kv("thread_id", threadId);
        }
        if (reservation != null) {
            return addReservationParams(strap, reservation);
        }
        return strap;
    }

    private static Strap addReservationParams(Strap strap, Reservation reservation) {
        strap.mo11637kv("hours_until_checkin", new AirDateTime(reservation.getCheckinDate().getTimeInMillisAtStartOfDay()).hoursFromNow());
        strap.mo11637kv("hours_until_checkout", new AirDateTime(reservation.getCheckoutDate().getTimeInMillisAtStartOfDay()).hoursFromNow());
        return strap;
    }

    public static void trackROItineraryClickWifi(long reservationId, Reservation reservation, long threadId) {
        AirbnbEventLogger.track("reservation_object", makeParams(RO_ITINERARY, "click_wifi", GENERAL, reservationId, reservation, threadId));
    }

    public static void trackROItineraryClickAlterReservation(long reservationId, Reservation reservation, long threadId, boolean pendingAlteration) {
        AirbnbEventLogger.track("reservation_object", makeParams(RO_ITINERARY, "click_alter_reservation", GENERAL, reservationId, reservation, threadId));
        if (pendingAlteration) {
            AlterationAnalytics.trackViewPendingAlterationFromItinerary(reservation, reservation.getFirstPendingAlteration());
        } else {
            AlterationAnalytics.trackAlterReservationFromItinerary(reservation);
        }
    }

    public static void trackRespondNowSelectOptionForReservation(String option, Reservation reservation) {
        AirbnbEventLogger.track(RESPOND_NOW, makeParams(RESERVATION_REQUEST, "select_response", GENERAL, reservation.getId(), reservation, -1).mo11639kv("response", option));
    }
}
