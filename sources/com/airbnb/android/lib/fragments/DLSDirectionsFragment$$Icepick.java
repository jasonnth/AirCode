package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.DLSDirectionsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DLSDirectionsFragment$$Icepick<T extends DLSDirectionsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9527H = new Helper("com.airbnb.android.lib.fragments.DLSDirectionsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9527H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9527H.putParcelable(state, "listing", target.listing);
    }
}
