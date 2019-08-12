package com.airbnb.android.booking.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.booking.fragments.PaymentManagerFragment;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import java.util.List;

public interface BookingActivityFacade {
    void bookReservation();

    void completeBooking(Reservation reservation);

    void doneWithArrivalDetails(String str);

    void doneWithBusinessTravelIdentification(TripType tripType);

    void doneWithCouponCode(Reservation reservation);

    void doneWithGuestIdentifications(List<GuestIdentity> list);

    void doneWithGuestPicking(GuestDetails guestDetails);

    void doneWithNoResults();

    void doneWithPaymentSelection(OldPaymentInstrument oldPaymentInstrument);

    void doneWithTripNote(String str);

    void fetchIdentityVerifications();

    GovernmentIdResult getGovernmentIdResult();

    FetchIdentityController getIdentityController();

    Listing getListing();

    String getMobileSearchSessionId();

    List<OldPaymentInstrument> getPaymentInstruments();

    PaymentManagerFragment getPaymentManagerFragment();

    Reservation getReservation();

    ReservationDetails getReservationDetails();

    void handleAlipayError();

    void handleBookingError(AirRequestNetworkException airRequestNetworkException);

    boolean hasReservation();

    void onBookingSummaryLoadEnd();

    boolean replaceVerifiedIdWithIdentityAsBookingStep();

    void setGovernmentIdResult(GovernmentIdResult governmentIdResult);

    void setProvidedGovernmentId(boolean z);

    boolean shouldUseIdentityFlowForFrozenReservation();

    void showArrivalDetails();

    void showCalendar();

    void showCouponCode();

    void showGuestDetails();

    void showGuestIdentifications();

    void showHouseRules();

    void showMessageHost();

    void showPaymentOptions();

    void showPriceBreakdown();

    void updateReservation();
}
