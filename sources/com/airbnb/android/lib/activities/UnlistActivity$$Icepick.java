package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.activities.UnlistActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class UnlistActivity$$Icepick<T extends UnlistActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9480H = new Helper("com.airbnb.android.lib.activities.UnlistActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9480H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9480H.putParcelable(state, "listing", target.listing);
    }
}
