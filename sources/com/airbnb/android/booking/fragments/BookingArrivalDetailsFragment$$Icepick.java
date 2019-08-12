package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.BookingArrivalDetailsFragment;
import com.airbnb.android.core.models.ArrivalDetails;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class BookingArrivalDetailsFragment$$Icepick<T extends BookingArrivalDetailsFragment> extends BookingV2BaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7427H = new Helper("com.airbnb.android.booking.fragments.BookingArrivalDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkInTimePhrase = f7427H.getString(state, "checkInTimePhrase");
            target.selectedCheckIn = f7427H.getString(state, "selectedCheckIn");
            target.arrivalDetails = (ArrivalDetails) f7427H.getParcelable(state, "arrivalDetails");
            target.hostName = f7427H.getString(state, "hostName");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7427H.putString(state, "checkInTimePhrase", target.checkInTimePhrase);
        f7427H.putString(state, "selectedCheckIn", target.selectedCheckIn);
        f7427H.putParcelable(state, "arrivalDetails", target.arrivalDetails);
        f7427H.putString(state, "hostName", target.hostName);
    }
}
