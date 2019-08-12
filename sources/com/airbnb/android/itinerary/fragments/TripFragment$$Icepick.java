package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import com.airbnb.android.itinerary.fragments.TripFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class TripFragment$$Icepick<T extends TripFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8332H = new Helper("com.airbnb.android.itinerary.fragments.TripFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f8332H.getString(state, "confirmationCode");
            target.location = f8332H.getString(state, "location");
            target.tripEvents = f8332H.getParcelableArrayList(state, "tripEvents");
            target.showNowIndicator = f8332H.getBoolean(state, "showNowIndicator");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8332H.putString(state, "confirmationCode", target.confirmationCode);
        f8332H.putString(state, "location", target.location);
        f8332H.putParcelableArrayList(state, "tripEvents", target.tripEvents);
        f8332H.putBoolean(state, "showNowIndicator", target.showNowIndicator);
    }
}
