package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.fragments.BookingEditTextFragment;
import com.airbnb.android.booking.fragments.BookingEditTextFragment.Type;
import com.airbnb.android.booking.utils.BookingUtil;
import com.airbnb.android.core.models.ReservationDetails;

public class TripPurposeBookingStep implements BookingStep {
    private final BookingController controller;

    public TripPurposeBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void show(boolean isBackButtonPressed) {
        ReservationDetails reservationDetails = this.controller.getReservationDetails();
        this.controller.getBookingActivityFacade().showFragment(BookingEditTextFragment.newInstance(this.controller.getListing().getPrimaryHost(), reservationDetails.businessTripNote(), Type.TripPurpose), BookingUtil.getTransitionType(isBackButtonPressed));
    }

    public boolean exclude() {
        return !this.controller.getReservationDetails().isBusinessTrip();
    }

    public boolean initialized() {
        return true;
    }

    public void onReservationLoaded() {
    }
}
