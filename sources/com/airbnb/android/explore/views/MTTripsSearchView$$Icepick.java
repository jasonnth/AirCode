package com.airbnb.android.explore.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.explore.views.MTTripsSearchView;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class MTTripsSearchView$$Icepick<T extends MTTripsSearchView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8588H = new Helper("com.airbnb.android.explore.views.MTTripsSearchView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.progress = f8588H.getFloat(state, "progress");
        target.heightProgress = f8588H.getFloat(state, "heightProgress");
        target.clearAllHidden = f8588H.getBoolean(state, "clearAllHidden");
        return super.restore(target, f8588H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8588H.putParent(super.save(target, p));
        f8588H.putFloat(state, "progress", target.progress);
        f8588H.putFloat(state, "heightProgress", target.heightProgress);
        f8588H.putBoolean(state, "clearAllHidden", target.clearAllHidden);
        return state;
    }
}
