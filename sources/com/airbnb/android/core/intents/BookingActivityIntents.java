package com.airbnb.android.core.intents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.utils.Check;

public final class BookingActivityIntents {
    public static final String EXTRA_FIRST_VERIFICATION_STEP = "extra_first_verification_step";
    public static final String EXTRA_HOST_MESSAGE = "extra_host_message";
    public static final String EXTRA_LISTING = "extra_listing";
    public static final String EXTRA_RESERVATION_DETAILS = "extra_reservation_details";
    public static final String EXTRA_SESSION_ID = "extra_mobile_session_id";

    private BookingActivityIntents() {
    }

    private static Class<? extends Activity> getBookingActivity(Context context) {
        return FeatureToggles.showP4Redesign(context) ? Activities.bookingV2() : Activities.booking();
    }

    public static Intent intentForBooking(Context context, Thread thread, Listing listing) {
        Check.notNull(thread);
        GuestDetails filterData = new GuestDetails().adultsCount(thread.getNumberOfGuests());
        return intentForBooking(context, listing, thread.getStartDate(), thread.getEndDate(), filterData, null, null, null);
    }

    public static Intent intentForBooking(Context context, Listing listing, AirDate checkIn, AirDate checkOut, GuestDetails guestDetails, TripPurpose tripPurpose, String searchSessionId, String firstVerificationStep) {
        return new Intent(context, getBookingActivity(context)).putExtra(EXTRA_LISTING, listing).putExtra(ReservationDetails.EXTRA_CHECK_IN, checkIn).putExtra(ReservationDetails.EXTRA_CHECK_OUT, checkOut).putExtra(ReservationDetails.EXTRA_GUEST_FILTER_DATA, guestDetails).putExtra(ReservationDetails.EXTRA_TRIP_PURPOSE, tripPurpose != null ? tripPurpose.ordinal() : -1).putExtra(EXTRA_SESSION_ID, searchSessionId).putExtra(EXTRA_FIRST_VERIFICATION_STEP, firstVerificationStep);
    }

    public static Intent intentForRebooking(Context context, Listing listing, ReservationDetails reservationDetails) {
        return new Intent(context, getBookingActivity(context)).putExtra(EXTRA_LISTING, listing).putExtra(EXTRA_RESERVATION_DETAILS, reservationDetails);
    }

    public static Intent intentForSpecialOffer(Context context, Thread thread, Listing listing) {
        SpecialOffer specialOffer = thread.getSpecialOffer();
        return new Intent(context, getBookingActivity(context)).putExtra(EXTRA_LISTING, listing).putExtra(ReservationDetails.EXTRA_CHECK_IN, specialOffer.getStartDate()).putExtra(ReservationDetails.EXTRA_CHECK_OUT, specialOffer.getStartDate().plusDays(specialOffer.getNights())).putExtra(ReservationDetails.EXTRA_SPECIAL_OFFER_ID, specialOffer.getId());
    }
}
