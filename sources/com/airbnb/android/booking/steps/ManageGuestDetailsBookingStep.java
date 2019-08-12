package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import icepick.State;

public class ManageGuestDetailsBookingStep implements BookingStep {
    private final BookingController controller;
    @State
    Boolean exclude;

    public ManageGuestDetailsBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void show(boolean isBackButtonPressed) {
        this.controller.getBookingActivityFacade().showGuestIdentifications(isBackButtonPressed, this.controller.getP4Steps());
    }

    public boolean exclude() {
        return !this.controller.getReservation().isGuestIdentificationsRequired();
    }

    public boolean initialized() {
        return this.controller.getReservation() != null;
    }

    public void onReservationLoaded() {
    }
}
