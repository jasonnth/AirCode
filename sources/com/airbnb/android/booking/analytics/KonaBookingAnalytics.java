package com.airbnb.android.booking.analytics;

import android.content.Context;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppboyAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.analytics.ROAnalytics;
import com.airbnb.android.utils.Strap;

public class KonaBookingAnalytics {
    public static final String BOOKING_SUMMARY_PAGE = "p4_summary";
    public static final String CONFIRM_AND_PAY_BUTTON = "confirm_and_pay_button";
    public static final String PAYMENT_OPTIONS_PAGE = "payment_options";

    public enum BookingSummaryRow {
        ArrivalDetailsRow("arrival_details_row"),
        PaymentOptionsRow("payment_options_row"),
        PriceRow("price_row"),
        CouponCodeRow("coupon_code_row"),
        GuestDetailsRow("guest_details_row"),
        DateRangeRow("date_range_row"),
        NightsRow("nights_row"),
        HouseRulesRow("house_rules_row"),
        TripPurposeRow("trip_purpose_row"),
        GovernmentIdRow("government_id_row"),
        PhoneNumberRow("phone_number_row"),
        EmailAddressRow("email_address_row");
        
        private final String name;

        private BookingSummaryRow(String name2) {
            this.name = name2;
        }

        public String toString() {
            return this.name;
        }
    }

    public static void trackUpdateGuestDetails(String page, GuestDetails guestsData, ReservationDetails reservationDetails, String mobileSearchSessionId) {
        trackClick(page, "save_guest_details", reservationDetails.toBasicAnalyticsStrap(mobileSearchSessionId).mix(ParcelStrap.make().mo9945kv("n_adults", guestsData.adultsCount()).mo9945kv("n_infants", guestsData.infantsCount()).mo9945kv("n_children", guestsData.childrenCount()).mo9947kv("pet_toggle", guestsData.isBringingPets())));
    }

    public static void trackPaymentOptionSelection(String method, OldPaymentInstrument instrument, ReservationDetails reservationDetails, String mobileSearchSessionId) {
        trackClick("payment_options", "select_method", reservationDetails.toBasicAnalyticsStrap(mobileSearchSessionId).mix(ParcelStrap.make().mo9946kv("method", method).mo9946kv("payment_instrument_id", String.valueOf(instrument.getId()))));
    }

    public static void trackTooltipClick(long reservationId, String mobileSearchSessionId) {
        ParcelStrap strap = ParcelStrap.make().mo9946kv("mobile_search_session_id", mobileSearchSessionId);
        if (reservationId > -1) {
            strap.mo9946kv("id_reservation", String.valueOf(reservationId));
        }
        trackClick("payment_breakdown", "tooltip", strap);
    }

    public static void trackClick(String page, String section, ParcelStrap additionalValues) {
        AirbnbEventLogger.track("p4_mobile", ParcelStrap.make().mo9946kv("page", page).mo9946kv(BaseAnalytics.SECTION, section).mo9946kv(BaseAnalytics.OPERATION, "click").mix(additionalValues).internal());
    }

    public static void trackImpression(Reservation reservation, ReservationDetails reservationDetails, String mobileSearchSessionId, Context context) {
        String str;
        String str2 = null;
        AirbnbEventLogger.track("p4_mobile", reservationDetails.toFullAnalyticsStrap(mobileSearchSessionId).mo9946kv("page", BOOKING_SUMMARY_PAGE).mo9946kv(BaseAnalytics.SECTION, ROAnalytics.GENERAL).mo9946kv(BaseAnalytics.OPERATION, "impression").internal());
        Listing listing = reservation.getListing();
        Strap make = Strap.make();
        String str3 = "checkin_date";
        if (reservation.getCheckinDate() != null) {
            str = reservation.getCheckinDate().getIsoDateString();
        } else {
            str = null;
        }
        Strap kv = make.mo11639kv(str3, str);
        String str4 = "checkout_date";
        if (reservation.getCheckoutDate() != null) {
            str2 = reservation.getCheckoutDate().getIsoDateString();
        }
        Strap info = kv.mo11639kv(str4, str2).mo11638kv("listing_id", listing.getId()).mo11639kv("listing_title", listing.getName()).mo11639kv("listing_city", listing.getCity()).mo11639kv("listing_state", listing.getState()).mo11639kv("listing_country", listing.getCountry()).mo11640kv("listing_instant_bookable", listing.isInstantBookEnabled()).mo11639kv("guest_first_name", reservation.getGuest().getFirstName()).mo11640kv("guest_is_host", reservation.getGuest().getListingsCount() > 0);
        MParticleAnalytics.logEvent("p4_impression", info);
        AppboyAnalytics.logEvent(context, "p4_impression", info);
    }

    public static void trackUpdate(String page, ReservationDetails reservationDetails, String mobileSearchSessionId) {
        AirbnbEventLogger.track("p4_mobile", reservationDetails.toFullAnalyticsStrap(mobileSearchSessionId).mo9946kv("page", page).mo9946kv(BaseAnalytics.SECTION, "reservation_detail").mo9946kv(BaseAnalytics.OPERATION, BaseAnalytics.UPDATE).internal());
    }

    public static void trackView(String page, String section, ParcelStrap additionalValues) {
        AirbnbEventLogger.track("p4_mobile", ParcelStrap.make().mo9946kv("page", page).mo9946kv(BaseAnalytics.SECTION, section).mo9946kv(BaseAnalytics.OPERATION, "view").mix(additionalValues).internal());
    }

    public static void trackError(String page, String section, ParcelStrap additionalValues) {
        AirbnbEventLogger.track("p4_mobile", ParcelStrap.make().mo9946kv("page", page).mo9946kv(BaseAnalytics.SECTION, section).mo9946kv(BaseAnalytics.OPERATION, "error").mix(additionalValues).internal());
    }
}
