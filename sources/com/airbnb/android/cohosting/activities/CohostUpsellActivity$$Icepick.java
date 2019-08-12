package com.airbnb.android.cohosting.activities;

import android.os.Bundle;
import com.airbnb.android.cohosting.activities.CohostUpsellActivity;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostUpsellActivity$$Icepick<T extends CohostUpsellActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7789H = new Helper("com.airbnb.android.cohosting.activities.CohostUpsellActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7789H.getParcelable(state, "listing");
            target.manager = (ListingManager) f7789H.getParcelable(state, "manager");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7789H.putParcelable(state, "listing", target.listing);
        f7789H.putParcelable(state, "manager", target.manager);
    }
}
