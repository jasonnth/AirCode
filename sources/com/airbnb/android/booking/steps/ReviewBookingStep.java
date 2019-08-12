package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.fragments.BookingReviewFragment;
import com.airbnb.android.booking.utils.BookingUtil;

public class ReviewBookingStep implements BookingStep {
    private final BookingController controller;

    public ReviewBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void show(boolean isBackButtonPressed) {
        if (isBackButtonPressed) {
            this.controller.getTotalBookingSteps(true);
        }
        this.controller.getBookingActivityFacade().showFragment(BookingReviewFragment.getInstance(), BookingUtil.getTransitionType(isBackButtonPressed));
    }

    public boolean exclude() {
        return false;
    }

    public void onReservationLoaded() {
    }

    public boolean initialized() {
        return true;
    }
}
