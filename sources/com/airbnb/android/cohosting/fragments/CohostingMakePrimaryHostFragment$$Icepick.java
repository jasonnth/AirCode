package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostingMakePrimaryHostFragment;
import com.airbnb.android.core.models.ListingManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingMakePrimaryHostFragment$$Icepick<T extends CohostingMakePrimaryHostFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7810H = new Helper("com.airbnb.android.cohosting.fragments.CohostingMakePrimaryHostFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingManager = (ListingManager) f7810H.getParcelable(state, "listingManager");
            target.isCheckedOnUpdateNotifCheckbox = f7810H.getBoolean(state, "isCheckedOnUpdateNotifCheckbox");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7810H.putParcelable(state, "listingManager", target.listingManager);
        f7810H.putBoolean(state, "isCheckedOnUpdateNotifCheckbox", target.isCheckedOnUpdateNotifCheckbox);
    }
}
