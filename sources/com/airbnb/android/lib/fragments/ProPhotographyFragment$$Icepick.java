package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.ProPhotographyFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ProPhotographyFragment$$Icepick<T extends ProPhotographyFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9544H = new Helper("com.airbnb.android.lib.fragments.ProPhotographyFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mSelectedListing = f9544H.getLong(state, "mSelectedListing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9544H.putLong(state, "mSelectedListing", target.mSelectedListing);
    }
}
