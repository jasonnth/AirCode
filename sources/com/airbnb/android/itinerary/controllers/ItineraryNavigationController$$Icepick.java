package com.airbnb.android.itinerary.controllers;

import android.os.Bundle;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ItineraryNavigationController$$Icepick<T extends ItineraryNavigationController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8327H = new Helper("com.airbnb.android.itinerary.controllers.ItineraryNavigationController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentFragmentTag = f8327H.getString(state, "currentFragmentTag");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8327H.putString(state, "currentFragmentTag", target.currentFragmentTag);
    }
}
