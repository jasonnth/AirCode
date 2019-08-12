package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.lib.activities.SplashScreenActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SplashScreenActivity$$Icepick<T extends SplashScreenActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9479H = new Helper("com.airbnb.android.lib.activities.SplashScreenActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.onCreateTimeNano = f9479H.getLong(state, "onCreateTimeNano");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9479H.putLong(state, "onCreateTimeNano", target.onCreateTimeNano);
    }
}
