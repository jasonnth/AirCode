package com.airbnb.android.lib.paidamenities.fragments.hostservice;

import android.os.Bundle;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostAmenityListFragment$$Icepick<T extends HostAmenityListFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9613H = new Helper("com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paidAmenities = f9613H.getParcelableArrayList(state, "paidAmenities");
            target.listingId = f9613H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9613H.putParcelableArrayList(state, "paidAmenities", target.paidAmenities);
        f9613H.putLong(state, "listingId", target.listingId);
    }
}
