package com.airbnb.android.places;

import android.os.Bundle;
import com.airbnb.android.places.ResyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ResyController$$Icepick<T extends ResyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f11164H = new Helper("com.airbnb.android.places.ResyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.resyState = (ResyState) f11164H.getParcelable(state, "resyState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f11164H.putParcelable(state, "resyState", target.resyState);
    }
}
