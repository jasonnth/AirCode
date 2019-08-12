package com.airbnb.android.contentframework.fragments;

import android.os.Bundle;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment;
import com.airbnb.android.contentframework.fragments.StoryFeedFragment.Mode;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class StoryFeedFragment$$Icepick<T extends StoryFeedFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8046H = new Helper("com.airbnb.android.contentframework.fragments.StoryFeedFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.topTiles = f8046H.getParcelableArrayList(state, "topTiles");
            target.loadedArticles = f8046H.getParcelableArrayList(state, "loadedArticles");
            target.paginationCursor = f8046H.getString(state, "paginationCursor");
            target.isLoading = f8046H.getBoolean(state, "isLoading");
            target.impressionStartTime = f8046H.getLong(state, "impressionStartTime");
            target.mode = (Mode) f8046H.getSerializable(state, "mode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8046H.putParcelableArrayList(state, "topTiles", target.topTiles);
        f8046H.putParcelableArrayList(state, "loadedArticles", target.loadedArticles);
        f8046H.putString(state, "paginationCursor", target.paginationCursor);
        f8046H.putBoolean(state, "isLoading", target.isLoading);
        f8046H.putLong(state, "impressionStartTime", target.impressionStartTime);
        f8046H.putSerializable(state, "mode", target.mode);
    }
}
