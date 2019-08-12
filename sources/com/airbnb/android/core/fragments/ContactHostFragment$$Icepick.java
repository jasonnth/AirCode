package com.airbnb.android.core.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.ContactHostFragment;
import com.airbnb.android.core.models.Listing;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ContactHostFragment$$Icepick<T extends ContactHostFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8446H = new Helper("com.airbnb.android.core.fragments.ContactHostFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f8446H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8446H.putParcelable(state, "listing", target.listing);
    }
}
