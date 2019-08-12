package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostingStopShareEarningsFragment;
import com.airbnb.android.core.models.ListingManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingStopShareEarningsFragment$$Icepick<T extends CohostingStopShareEarningsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7813H = new Helper("com.airbnb.android.cohosting.fragments.CohostingStopShareEarningsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingManager = (ListingManager) f7813H.getParcelable(state, "listingManager");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7813H.putParcelable(state, "listingManager", target.listingManager);
    }
}
