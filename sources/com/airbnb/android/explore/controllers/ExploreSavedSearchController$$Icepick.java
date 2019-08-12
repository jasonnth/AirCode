package com.airbnb.android.explore.controllers;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.explore.controllers.ExploreSavedSearchController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExploreSavedSearchController$$Icepick<T extends ExploreSavedSearchController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8578H = new Helper("com.airbnb.android.explore.controllers.ExploreSavedSearchController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.savedSearchId = f8578H.getString(state, "savedSearchId");
            target.source = f8578H.getString(state, "source");
            target.savedAt = (AirDateTime) f8578H.getParcelable(state, "savedAt");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8578H.putString(state, "savedSearchId", target.savedSearchId);
        f8578H.putString(state, "source", target.source);
        f8578H.putParcelable(state, "savedAt", target.savedAt);
    }
}
