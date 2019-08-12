package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.wishlists.WishListDetailsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WishListDetailsFragment$$Icepick<T extends WishListDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2678H = new Helper("com.airbnb.android.wishlists.WishListDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkIn = (AirDate) f2678H.getParcelable(state, "checkIn");
            target.checkOut = (AirDate) f2678H.getParcelable(state, "checkOut");
            target.guestFilters = (GuestDetails) f2678H.getParcelable(state, "guestFilters");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2678H.putParcelable(state, "checkIn", target.checkIn);
        f2678H.putParcelable(state, "checkOut", target.checkOut);
        f2678H.putParcelable(state, "guestFilters", target.guestFilters);
    }
}
