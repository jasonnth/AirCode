package com.airbnb.android.p011p3;

import android.os.Bundle;
import com.airbnb.android.p011p3.P3MapFragment;
import com.google.android.gms.maps.model.LatLng;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.p3.P3MapFragment$$Icepick */
public class P3MapFragment$$Icepick<T extends P3MapFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10536H = new Helper("com.airbnb.android.p3.P3MapFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mapCenter = (LatLng) f10536H.getParcelable(state, "mapCenter");
            target.mapZoom = f10536H.getInt(state, "mapZoom");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10536H.putParcelable(state, "mapCenter", target.mapCenter);
        f10536H.putInt(state, "mapZoom", target.mapZoom);
    }
}
