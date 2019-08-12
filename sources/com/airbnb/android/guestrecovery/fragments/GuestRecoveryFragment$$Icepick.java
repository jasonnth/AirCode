package com.airbnb.android.guestrecovery.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.guestrecovery.fragments.GuestRecoveryFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class GuestRecoveryFragment$$Icepick<T extends GuestRecoveryFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8812H = new Helper("com.airbnb.android.guestrecovery.fragments.GuestRecoveryFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservation = (Reservation) f8812H.getParcelable(state, "reservation");
            target.similarListings = f8812H.getParcelableArrayList(state, "similarListings");
            target.reservationStatus = (ReservationStatus) f8812H.getParcelable(state, "reservationStatus");
            target.confirmationCode = f8812H.getString(state, "confirmationCode");
            target.reservationId = f8812H.getLong(state, "reservationId");
            target.hasSetSimilarListings = f8812H.getBoolean(state, "hasSetSimilarListings");
            target.hasSetReservation = f8812H.getBoolean(state, "hasSetReservation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8812H.putParcelable(state, "reservation", target.reservation);
        f8812H.putParcelableArrayList(state, "similarListings", target.similarListings);
        f8812H.putParcelable(state, "reservationStatus", target.reservationStatus);
        f8812H.putString(state, "confirmationCode", target.confirmationCode);
        f8812H.putLong(state, "reservationId", target.reservationId);
        f8812H.putBoolean(state, "hasSetSimilarListings", target.hasSetSimilarListings);
        f8812H.putBoolean(state, "hasSetReservation", target.hasSetReservation);
    }
}
