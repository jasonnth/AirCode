package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.fragments.BookingHouseRulesFragment;
import com.airbnb.android.booking.utils.BookingUtil;

public class HouseRulesBookingStep implements BookingStep {
    private final BookingController controller;

    public HouseRulesBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void show(boolean isBackButtonPressed) {
        this.controller.getBookingActivityFacade().showFragment(BookingHouseRulesFragment.newInstance(this.controller.getReservation()), BookingUtil.getTransitionType(isBackButtonPressed));
    }

    public boolean exclude() {
        return !this.controller.getListing().hasHouseRules();
    }

    public boolean initialized() {
        return this.controller.getReservation() != null;
    }

    public void onReservationLoaded() {
    }
}
