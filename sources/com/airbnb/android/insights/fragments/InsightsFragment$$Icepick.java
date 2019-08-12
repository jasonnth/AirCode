package com.airbnb.android.insights.fragments;

import android.os.Bundle;
import com.airbnb.android.insights.fragments.InsightsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InsightsFragment$$Icepick<T extends InsightsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9454H = new Helper("com.airbnb.android.insights.fragments.InsightsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.insightToLoadingStateMap = (LinkedHashMap) f9454H.getSerializable(state, "insightToLoadingStateMap");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9454H.putSerializable(state, "insightToLoadingStateMap", target.insightToLoadingStateMap);
    }
}
