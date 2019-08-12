package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import com.airbnb.android.explore.fragments.MTExploreParentFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class MTExploreParentFragment$$Icepick<T extends MTExploreParentFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8582H = new Helper("com.airbnb.android.explore.fragments.MTExploreParentFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.showPlaylistOnResume = f8582H.getBoolean(state, "showPlaylistOnResume");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8582H.putBoolean(state, "showPlaylistOnResume", target.showPlaylistOnResume);
    }
}
