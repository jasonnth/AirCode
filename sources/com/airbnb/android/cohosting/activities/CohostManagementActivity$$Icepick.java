package com.airbnb.android.cohosting.activities;

import android.os.Bundle;
import com.airbnb.android.cohosting.activities.CohostManagementActivity;
import com.airbnb.android.core.models.Listing;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostManagementActivity$$Icepick<T extends CohostManagementActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7786H = new Helper("com.airbnb.android.cohosting.activities.CohostManagementActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingId = f7786H.getLong(state, "listingId");
            target.listing = (Listing) f7786H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7786H.putLong(state, "listingId", target.listingId);
        f7786H.putParcelable(state, "listing", target.listing);
    }
}
