package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.BookingV2BaseFragment;
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

public class BookingV2BaseFragment$$Icepick<T extends BookingV2BaseFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7432H = new Helper("com.airbnb.android.booking.fragments.BookingV2BaseFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7432H.getParcelable(state, "listing");
            target.reservation = (Reservation) f7432H.getParcelable(state, "reservation");
            target.reservationDetails = (ReservationDetails) f7432H.getParcelable(state, "reservationDetails");
            target.price = (Price) f7432H.getParcelable(state, EditPriceFragment.RESULT_PRICE);
            target.mobileSearchSessionId = f7432H.getString(state, "mobileSearchSessionId");
            target.pendingGuestDetailsUpdate = f7432H.getBoolean(state, "pendingGuestDetailsUpdate");
            target.hostMessage = f7432H.getString(state, "hostMessage");
            target.defaultBusinessTravelOn = f7432H.getBoolean(state, "defaultBusinessTravelOn");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7432H.putParcelable(state, "listing", target.listing);
        f7432H.putParcelable(state, "reservation", target.reservation);
        f7432H.putParcelable(state, "reservationDetails", target.reservationDetails);
        f7432H.putParcelable(state, EditPriceFragment.RESULT_PRICE, target.price);
        f7432H.putString(state, "mobileSearchSessionId", target.mobileSearchSessionId);
        f7432H.putBoolean(state, "pendingGuestDetailsUpdate", target.pendingGuestDetailsUpdate);
        f7432H.putString(state, "hostMessage", target.hostMessage);
        f7432H.putBoolean(state, "defaultBusinessTravelOn", target.defaultBusinessTravelOn);
    }
}
