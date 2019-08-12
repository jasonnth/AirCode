package com.airbnb.android.booking.steps;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.PostBookingActivityIntents;
import com.airbnb.android.core.models.Reservation;

public class PostBookingStep implements BookingStep {
    private final BookingController controller;

    public PostBookingStep(BookingController controller2) {
        this.controller = controller2;
    }

    public void restoreInstanceState(Bundle savedState) {
    }

    public void onSaveInstanceState(Bundle outState) {
    }

    public void show(boolean isBack) {
        this.controller.getBookingActivityFacade().startActivity(PostBookingActivityIntents.intentForReservation(this.controller.getContext(), this.controller.getReservation()));
        this.controller.getBookingActivityFacade().finishOK();
    }

    public boolean exclude() {
        Reservation reservation = this.controller.getReservation();
        return reservation.isCheckpointed() && !IdentityVerificationUtil.shouldUseIdentityFlowForFrozenReservation(reservation, this.controller.getVerificationUser());
    }

    public boolean initialized() {
        return this.controller.getReservation() != null;
    }

    public void onReservationLoaded() {
    }
}
