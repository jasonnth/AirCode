package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.BookingHouseRulesFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.User;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class BookingHouseRulesFragment$$Icepick<T extends BookingHouseRulesFragment> extends BookingV2BaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7429H = new Helper("com.airbnb.android.booking.fragments.BookingHouseRulesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7429H.getParcelable(state, "listing");
            target.host = (User) f7429H.getParcelable(state, TripRole.ROLE_KEY_HOST);
            target.hasPastBookings = f7429H.getBoolean(state, "hasPastBookings");
            target.isForLongTermStay = f7429H.getBoolean(state, "isForLongTermStay");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7429H.putParcelable(state, "listing", target.listing);
        f7429H.putParcelable(state, TripRole.ROLE_KEY_HOST, target.host);
        f7429H.putBoolean(state, "hasPastBookings", target.hasPastBookings);
        f7429H.putBoolean(state, "isForLongTermStay", target.isForLongTermStay);
    }
}
