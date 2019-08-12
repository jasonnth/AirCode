package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import com.airbnb.android.listing.LYSCollection;
import com.airbnb.android.listyourspacedls.fragments.LYSLandingFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSLandingFragment$$Icepick<T extends LYSLandingFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9934H = new Helper("com.airbnb.android.listyourspacedls.fragments.LYSLandingFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.previousMaxReachedCollection = (LYSCollection) f9934H.getSerializable(state, "previousMaxReachedCollection");
            target.hasAutoShownPublishAnimation = f9934H.getBoolean(state, "hasAutoShownPublishAnimation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9934H.putSerializable(state, "previousMaxReachedCollection", target.previousMaxReachedCollection);
        f9934H.putBoolean(state, "hasAutoShownPublishAnimation", target.hasAutoShownPublishAnimation);
    }
}
