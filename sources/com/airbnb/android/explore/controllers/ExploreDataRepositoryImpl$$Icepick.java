package com.airbnb.android.explore.controllers;

import android.os.Bundle;
import com.airbnb.android.explore.controllers.ExploreDataRepositoryImpl;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExploreDataRepositoryImpl$$Icepick<T extends ExploreDataRepositoryImpl> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8573H = new Helper("com.airbnb.android.explore.controllers.ExploreDataRepositoryImpl$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.areExploreTabsLoading = f8573H.getBoolean(state, "areExploreTabsLoading");
            target.loadingTabSections = f8573H.getStringArrayList(state, "loadingTabSections");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8573H.putBoolean(state, "areExploreTabsLoading", target.areExploreTabsLoading);
        f8573H.putStringArrayList(state, "loadingTabSections", target.loadingTabSections);
    }
}
