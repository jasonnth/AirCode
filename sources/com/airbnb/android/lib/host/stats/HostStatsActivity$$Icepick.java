package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.core.activities.AutoFragmentActivity$$Icepick;
import com.airbnb.android.lib.host.stats.HostStatsActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class HostStatsActivity$$Icepick<T extends HostStatsActivity> extends AutoFragmentActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9597H = new Helper("com.airbnb.android.lib.host.stats.HostStatsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listings = f9597H.getParcelableArrayList(state, "listings");
            target.bundleForListingSelector = f9597H.getBundle(state, "bundleForListingSelector");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9597H.putParcelableArrayList(state, "listings", target.listings);
        f9597H.putBundle(state, "bundleForListingSelector", target.bundleForListingSelector);
    }
}
