package com.airbnb.android.explore.controllers;

import android.os.Bundle;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreMode;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExploreNavigationController$$Icepick<T extends ExploreNavigationController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8577H = new Helper("com.airbnb.android.explore.controllers.ExploreNavigationController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentMode = (ExploreMode) f8577H.getSerializable(state, "currentMode");
            target.activeTabId = f8577H.getString(state, "activeTabId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8577H.putSerializable(state, "currentMode", target.currentMode);
        f8577H.putString(state, "activeTabId", target.activeTabId);
    }
}
