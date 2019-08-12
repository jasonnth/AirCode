package com.airbnb.android.booking.controller;

import android.os.Bundle;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.lib.fragments.managelisting.EditPriceFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BookingController$$Icepick<T extends BookingController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7423H = new Helper("com.airbnb.android.booking.controller.BookingController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7423H.getParcelable(state, "listing");
            target.reservation = (Reservation) f7423H.getParcelable(state, "reservation");
            target.reservationDetails = (ReservationDetails) f7423H.getParcelable(state, "reservationDetails");
            target.price = (Price) f7423H.getParcelable(state, EditPriceFragment.RESULT_PRICE);
            target.mobileSearchSessionId = f7423H.getString(state, "mobileSearchSessionId");
            target.currentStep = f7423H.getInt(state, "currentStep");
            target.navForward = f7423H.getBoolean(state, "navForward");
            target.hostMessage = f7423H.getString(state, "hostMessage");
            target.stepCounter = f7423H.getInt(state, "stepCounter");
            target.totalValidSteps = f7423H.getInt(state, "totalValidSteps");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7423H.putParcelable(state, "listing", target.listing);
        f7423H.putParcelable(state, "reservation", target.reservation);
        f7423H.putParcelable(state, "reservationDetails", target.reservationDetails);
        f7423H.putParcelable(state, EditPriceFragment.RESULT_PRICE, target.price);
        f7423H.putString(state, "mobileSearchSessionId", target.mobileSearchSessionId);
        f7423H.putInt(state, "currentStep", target.currentStep);
        f7423H.putBoolean(state, "navForward", target.navForward);
        f7423H.putString(state, "hostMessage", target.hostMessage);
        f7423H.putInt(state, "stepCounter", target.stepCounter);
        f7423H.putInt(state, "totalValidSteps", target.totalValidSteps);
    }
}
