package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import com.airbnb.android.itinerary.fragments.ItineraryParentFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ItineraryParentFragment$$Icepick<T extends ItineraryParentFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8331H = new Helper("com.airbnb.android.itinerary.fragments.ItineraryParentFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isFirstLoad = f8331H.getBoolean(state, "isFirstLoad");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8331H.putBoolean(state, "isFirstLoad", target.isFirstLoad);
    }
}
