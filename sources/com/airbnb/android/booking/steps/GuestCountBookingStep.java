package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.fragments.BookingGuestsPickerFragment.BookingGuestsPickerFragmentBuilder;
import com.airbnb.android.booking.utils.BookingUtil;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.GuestDetails;
import icepick.State;

public class GuestCountBookingStep implements BookingStep {
    private final BookingController controller;
    @State
    Boolean exclude;

    public GuestCountBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
        if (savedState != null) {
            IcepickWrapper.restoreInstanceState(this, savedState);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void show(boolean isBackButtonPressed) {
        this.controller.getBookingActivityFacade().showFragment(new BookingGuestsPickerFragmentBuilder(new GuestDetails().reservationDetails(this.controller.getReservationDetails()), NavigationTag.P3.trackingName).setListing(this.controller.getListing()).setShowBlockInstantBookWarning(true).build(), BookingUtil.getTransitionType(isBackButtonPressed));
    }

    public boolean exclude() {
        if (this.exclude == null) {
            this.exclude = Boolean.valueOf(this.controller.getReservationDetails().getGuestDetails().totalGuestCount() > 0);
        }
        return this.exclude.booleanValue();
    }

    public boolean initialized() {
        return true;
    }

    public void onReservationLoaded() {
    }
}
