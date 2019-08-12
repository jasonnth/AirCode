package com.airbnb.android.core.analytics;

import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import java.util.Collection;
import org.json.JSONArray;

public class TripsAnalytics extends BaseAnalytics {
    private static final String CANCELLATION_POLICY = "cancellation_policy";
    private static final String CONFIRMATION_CODE = "confirmation_code";
    private static final String ID_RESERVATION = "id_reservation";
    private static final String RESERVATION_EVENT = "reservation_details";
    private static final String RESERVATION_OBJECT = "reservation_object";

    public static void trackInquiryLoad(long threadId) {
        trackLoad(Strap.make().mo11639kv("page", "reservation_object").mo11638kv("thread_id", threadId));
    }

    public static void trackReservationLoad(String confirmationCode) {
        trackLoad(Strap.make().mo11639kv("page", "reservation_object").mo11639kv("confirmation_code", confirmationCode));
    }

    public static void trackReservationLoad(long reservationId) {
        trackLoad(Strap.make().mo11639kv("page", "reservation_object").mo11638kv(ID_RESERVATION, reservationId));
    }

    public static void trackReservationView(Reservation reservation, ReservationStatus status, long listingId, AirDate checkIn, AirDate checkOut, String cancellationPolicyKey, int guestCount) {
        Strap params = Strap.make().mo11639kv("page", "reservation_object").mo11639kv("reservation_stage", status.key).mo11638kv("id_listing", listingId).mo11639kv("checkin_date", checkIn.getIsoDateString()).mo11639kv("checkout_date", checkOut.getIsoDateString()).mo11637kv("guest_count", guestCount).mo11639kv("cancellation_policy", cancellationPolicyKey);
        if (reservation != null) {
            addArrivalDetailsParam(params, reservation);
        }
        trackView(reservation, params);
    }

    private static void addArrivalDetailsParam(Strap params, Reservation reservation) {
        ArrivalDetails arrivalDetails = reservation.getArrivalDetails();
        if (arrivalDetails != null) {
            params.mo11639kv("guest_params", Strap.make().mo11637kv(FindTweenAnalytics.ADULTS, arrivalDetails.getNumberOfAdults()).mo11637kv(FindTweenAnalytics.CHILDREN, arrivalDetails.getNumberOfChildren()).mo11637kv(FindTweenAnalytics.INFANTS, arrivalDetails.getNumberOfInfants()).mo11640kv(FindTweenAnalytics.PETS, arrivalDetails.isBringingPets()).toJsonString());
        }
    }

    public static void trackCancellationPolicyView(Reservation reservation, String cancellationPolicyKey) {
        trackView(reservation, Strap.make().mo11639kv("page", "reservation_object").mo11639kv(BaseAnalytics.SECTION, "cancellation_policy").mo11639kv("cancellation_policy", cancellationPolicyKey));
    }

    public static void trackPaymentBreakdownView(Reservation reservation) {
        trackView(reservation, Strap.make().mo11639kv("page", "reservation_object").mo11639kv(BaseAnalytics.SECTION, "payment_breakdown"));
    }

    public static void trackDirectionsView(Reservation reservation, String directions) {
        trackView(reservation, Strap.make().mo11639kv("page", "reservation_object").mo11639kv(BaseAnalytics.SECTION, ListingRequestConstants.JSON_DIRECTIONS_KEY).mo11639kv(ListingRequestConstants.JSON_DIRECTIONS_KEY, directions));
    }

    public static void trackHouseRulesView(Reservation reservation, Listing listing) {
        Strap params = Strap.make().mo11639kv("page", "reservation_object").mo11639kv(BaseAnalytics.SECTION, ListingRequestConstants.JSON_HOUSE_RULES_KEY);
        JSONArray jsonRules = new JSONArray();
        if (!ListUtils.isEmpty((Collection<?>) listing.getGuestControls().getStructuredHouseRules())) {
            for (String rule : listing.getGuestControls().getStructuredHouseRules()) {
                jsonRules.put(rule);
            }
        }
        if (!TextUtils.isEmpty(listing.getAdditionalHouseRules())) {
            jsonRules.put(listing.getAdditionalHouseRules());
        }
        params.mo11639kv(ListingRequestConstants.JSON_HOUSE_RULES_KEY, jsonRules.toString());
        trackView(reservation, params);
    }

    public static void trackCancelView(Reservation reservation) {
        trackView(reservation, Strap.make().mo11639kv("page", "reservation_object").mo11639kv(BaseAnalytics.SECTION, BaseAnalytics.CANCEL));
    }

    public static void trackCancelConfirm(Reservation reservation) {
        trackView(reservation, Strap.make().mo11639kv("page", "cancel_confirmation"));
    }

    public static void trackCancelDayOf(Reservation reservation) {
        trackView(reservation, Strap.make().mo11639kv("page", "cancel_day_of"));
    }

    private static void trackView(Reservation reservation, Strap params) {
        Strap baseParams = Strap.make().mo11639kv("action", "view");
        if (reservation != null) {
            params.mo11638kv(ID_RESERVATION, reservation.getId()).mo11639kv("confirmation_code", reservation.getConfirmationCode());
            if (reservation.isSharedItinerary() && reservation.getGuest() != null) {
                params.mo11638kv("shared_by", reservation.getGuest().getId());
            }
        }
        AirbnbEventLogger.track(RESERVATION_EVENT, baseParams.mix(params));
    }

    private static void trackLoad(Strap params) {
        AirbnbEventLogger.track(RESERVATION_EVENT, Strap.make().mo11639kv("action", "load").mix(params));
    }

    public static void trackTripsHomeView() {
        AirbnbEventLogger.track("trips", Strap.make().mo11639kv("page", "trips_home").mo11639kv("action", "view"));
    }
}
