package com.airbnb.android.insights.fragments.details;

import android.os.Bundle;
import com.airbnb.android.insights.fragments.details.InsightsNightlyPriceFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class InsightsNightlyPriceFragment$$Icepick<T extends InsightsNightlyPriceFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9455H = new Helper("com.airbnb.android.insights.fragments.details.InsightsNightlyPriceFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.inEditMode = f9455H.getBoolean(state, "inEditMode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9455H.putBoolean(state, "inEditMode", target.inEditMode);
    }
}
