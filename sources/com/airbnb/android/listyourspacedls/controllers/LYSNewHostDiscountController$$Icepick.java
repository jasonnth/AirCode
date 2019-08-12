package com.airbnb.android.listyourspacedls.controllers;

import android.os.Bundle;
import com.airbnb.android.listyourspacedls.controllers.LYSNewHostDiscountController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSNewHostDiscountController$$Icepick<T extends LYSNewHostDiscountController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9926H = new Helper("com.airbnb.android.listyourspacedls.controllers.LYSNewHostDiscountController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isEnabled = f9926H.getBoxedBoolean(state, "isEnabled");
            target.radioRowsEnabled = f9926H.getBoolean(state, "radioRowsEnabled");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9926H.putBoxedBoolean(state, "isEnabled", target.isEnabled);
        f9926H.putBoolean(state, "radioRowsEnabled", target.radioRowsEnabled);
    }
}
