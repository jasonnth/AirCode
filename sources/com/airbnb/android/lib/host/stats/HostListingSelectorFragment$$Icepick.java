package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.lib.host.stats.HostListingSelectorFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostListingSelectorFragment$$Icepick<T extends HostListingSelectorFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9595H = new Helper("com.airbnb.android.lib.host.stats.HostListingSelectorFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.moreListingsToLoad = f9595H.getBoolean(state, "moreListingsToLoad");
            target.isFetchingListings = f9595H.getBoolean(state, "isFetchingListings");
            target.showAllListingsButton = f9595H.getBoolean(state, "showAllListingsButton");
            target.listingViews = f9595H.getInt(state, "listingViews");
            target.averageOverallRating = f9595H.getDouble(state, "averageOverallRating");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9595H.putBoolean(state, "moreListingsToLoad", target.moreListingsToLoad);
        f9595H.putBoolean(state, "isFetchingListings", target.isFetchingListings);
        f9595H.putBoolean(state, "showAllListingsButton", target.showAllListingsButton);
        f9595H.putInt(state, "listingViews", target.listingViews);
        f9595H.putDouble(state, "averageOverallRating", target.averageOverallRating);
    }
}
