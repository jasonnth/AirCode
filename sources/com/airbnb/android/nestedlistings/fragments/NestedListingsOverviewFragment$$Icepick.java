package com.airbnb.android.nestedlistings.fragments;

import android.os.Bundle;
import com.airbnb.android.nestedlistings.fragments.NestedListingsOverviewFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class NestedListingsOverviewFragment$$Icepick<T extends NestedListingsOverviewFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10529H = new Helper("com.airbnb.android.nestedlistings.fragments.NestedListingsOverviewFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.initialLoadDone = f10529H.getBoolean(state, "initialLoadDone");
            target.currentNestedListings = (HashMap) f10529H.getSerializable(state, "currentNestedListings");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10529H.putBoolean(state, "initialLoadDone", target.initialLoadDone);
        f10529H.putSerializable(state, "currentNestedListings", target.currentNestedListings);
    }
}
