package com.airbnb.android.core.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AutoFragmentActivity$$Icepick<T extends AutoFragmentActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8420H = new Helper("com.airbnb.android.core.activities.AutoFragmentActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.allowAccessWithoutSession = f8420H.getBoolean(state, "allowAccessWithoutSession");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8420H.putBoolean(state, "allowAccessWithoutSession", target.allowAccessWithoutSession);
    }
}
