package com.airbnb.android.core.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.views.MovablePinMap;
import com.google.android.gms.maps.model.LatLng;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class MovablePinMap$$Icepick<T extends MovablePinMap> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8505H = new Helper("com.airbnb.android.core.views.MovablePinMap$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.hasUserTriggeredCameraMove = f8505H.getBoolean(state, "hasUserTriggeredCameraMove");
        target.initialLocation = (LatLng) f8505H.getParcelable(state, "initialLocation");
        target.currentLocation = (LatLng) f8505H.getParcelable(state, "currentLocation");
        target.currentZoom = f8505H.getInt(state, "currentZoom");
        return super.restore(target, f8505H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8505H.putParent(super.save(target, p));
        f8505H.putBoolean(state, "hasUserTriggeredCameraMove", target.hasUserTriggeredCameraMove);
        f8505H.putParcelable(state, "initialLocation", target.initialLocation);
        f8505H.putParcelable(state, "currentLocation", target.currentLocation);
        f8505H.putInt(state, "currentZoom", target.currentZoom);
        return state;
    }
}
