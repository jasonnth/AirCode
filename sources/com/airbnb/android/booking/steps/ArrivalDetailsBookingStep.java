package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.fragments.BookingArrivalDetailsFragment;
import com.airbnb.android.booking.utils.BookingUtil;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;

public class ArrivalDetailsBookingStep implements BookingStep {
    private final BookingController controller;

    public ArrivalDetailsBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void show(boolean isBackButtonPressed) {
        this.controller.getBookingActivityFacade().showFragment(BookingArrivalDetailsFragment.newInstance(this.controller.getReservation().getListing(), this.controller.getReservation(), this.controller.getReservationDetails().checkInHour()), BookingUtil.getTransitionType(isBackButtonPressed));
    }

    public boolean exclude() {
        ReservationDetails reservationDetails = this.controller.getReservationDetails();
        Reservation reservation = this.controller.getReservation();
        return !reservationDetails.isCheckInTimeRequired().booleanValue() || reservation.getArrivalDetails() == null || reservation.getArrivalDetails().getCheckinTimeSelectionOptions() == null || reservation.getArrivalDetails().getCheckinTimeSelectionOptions().isEmpty();
    }

    public boolean initialized() {
        return this.controller.getReservation() != null;
    }

    public void onReservationLoaded() {
    }
}
