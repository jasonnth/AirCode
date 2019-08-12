package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import com.airbnb.android.cohosting.fragments.CohostUpsellFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostUpsellFragment$$Icepick<T extends CohostUpsellFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7804H = new Helper("com.airbnb.android.cohosting.fragments.CohostUpsellFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f7804H.getParcelable(state, "listing");
            target.listingManager = (ListingManager) f7804H.getParcelable(state, "listingManager");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7804H.putParcelable(state, "listing", target.listing);
        f7804H.putParcelable(state, "listingManager", target.listingManager);
    }
}
