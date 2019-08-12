package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.lib.activities.GuestRatingsHelpViewPagerActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class GuestRatingsHelpViewPagerActivity$$Icepick<T extends GuestRatingsHelpViewPagerActivity> extends ViewPagerActivity$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9464H = new Helper("com.airbnb.android.lib.activities.GuestRatingsHelpViewPagerActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingId = f9464H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9464H.putLong(state, "listingId", target.listingId);
    }
}
