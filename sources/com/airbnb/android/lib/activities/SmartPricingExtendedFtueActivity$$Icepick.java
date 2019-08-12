package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.lib.activities.SmartPricingExtendedFtueActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SmartPricingExtendedFtueActivity$$Icepick<T extends SmartPricingExtendedFtueActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9477H = new Helper("com.airbnb.android.lib.activities.SmartPricingExtendedFtueActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.forExtendedFtue = f9477H.getBoolean(state, "forExtendedFtue");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9477H.putBoolean(state, "forExtendedFtue", target.forExtendedFtue);
    }
}
