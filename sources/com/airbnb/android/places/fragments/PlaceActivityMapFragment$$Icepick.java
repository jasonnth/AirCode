package com.airbnb.android.places.fragments;

import android.os.Bundle;
import com.airbnb.android.places.fragments.PlaceActivityMapFragment;
import com.google.android.gms.maps.model.LatLng;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PlaceActivityMapFragment$$Icepick<T extends PlaceActivityMapFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f11166H = new Helper("com.airbnb.android.places.fragments.PlaceActivityMapFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mapCenter = (LatLng) f11166H.getParcelable(state, "mapCenter");
            target.mapZoom = f11166H.getInt(state, "mapZoom");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f11166H.putParcelable(state, "mapCenter", target.mapCenter);
        f11166H.putInt(state, "mapZoom", target.mapZoom);
    }
}
