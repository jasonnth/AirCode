package com.airbnb.android.core.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.DLSHouseRulesFragment;
import com.airbnb.android.core.models.Listing;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSHouseRulesFragment$$Icepick<T extends DLSHouseRulesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8449H = new Helper("com.airbnb.android.core.fragments.DLSHouseRulesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f8449H.getParcelable(state, "listing");
            target.forBooking = f8449H.getBoolean(state, "forBooking");
            target.hasPastBookings = f8449H.getBoolean(state, "hasPastBookings");
            target.isForLongTermStay = f8449H.getBoolean(state, "isForLongTermStay");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8449H.putParcelable(state, "listing", target.listing);
        f8449H.putBoolean(state, "forBooking", target.forBooking);
        f8449H.putBoolean(state, "hasPastBookings", target.hasPastBookings);
        f8449H.putBoolean(state, "isForLongTermStay", target.isForLongTermStay);
    }
}
