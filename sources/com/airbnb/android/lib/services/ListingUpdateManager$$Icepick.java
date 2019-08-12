package com.airbnb.android.lib.services;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.services.ListingUpdateManager;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ListingUpdateManager$$Icepick<T extends ListingUpdateManager> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9659H = new Helper("com.airbnb.android.lib.services.ListingUpdateManager$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9659H.getParcelable(state, "listing");
            target.fieldsToUpdate = (ParcelStrap) f9659H.getParcelable(state, "fieldsToUpdate");
            target.fieldsInFlight = (ParcelStrap) f9659H.getParcelable(state, "fieldsInFlight");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9659H.putParcelable(state, "listing", target.listing);
        f9659H.putParcelable(state, "fieldsToUpdate", target.fieldsToUpdate);
        f9659H.putParcelable(state, "fieldsInFlight", target.fieldsInFlight);
    }
}
