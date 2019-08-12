package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.AmenitiesAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AmenitiesAdapter$$Icepick<T extends AmenitiesAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9783H = new Helper("com.airbnb.android.listing.adapters.AmenitiesAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.amenityMap = (HashMap) f9783H.getSerializable(state, "amenityMap");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9783H.putSerializable(state, "amenityMap", target.amenityMap);
    }
}
