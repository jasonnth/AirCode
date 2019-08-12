package com.airbnb.android.listyourspacedls;

import android.os.Bundle;
import com.airbnb.android.listyourspacedls.LYSHomeActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSHomeActivity$$Icepick<T extends LYSHomeActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9810H = new Helper("com.airbnb.android.listyourspacedls.LYSHomeActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.progress = f9810H.getFloat(state, "progress");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9810H.putFloat(state, "progress", target.progress);
    }
}
