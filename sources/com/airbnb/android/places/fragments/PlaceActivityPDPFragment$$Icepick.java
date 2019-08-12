package com.airbnb.android.places.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.places.fragments.PlaceActivityPDPFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PlaceActivityPDPFragment$$Icepick<T extends PlaceActivityPDPFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f11167H = new Helper("com.airbnb.android.places.fragments.PlaceActivityPDPFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.activityModel = (PlaceActivity) f11167H.getParcelable(state, "activityModel");
            target.addToItineraryDateTime = (AirDateTime) f11167H.getParcelable(state, "addToItineraryDateTime");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f11167H.putParcelable(state, "activityModel", target.activityModel);
        f11167H.putParcelable(state, "addToItineraryDateTime", target.addToItineraryDateTime);
    }
}
