package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import com.airbnb.android.cityregistration.fragments.CityRegistrationBaseFragment;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationBaseFragment$$Icepick<T extends CityRegistrationBaseFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7671H = new Helper("com.airbnb.android.cityregistration.fragments.CityRegistrationBaseFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingRegistrationProcess = (ListingRegistrationProcess) f7671H.getParcelable(state, "listingRegistrationProcess");
            target.listing = (Listing) f7671H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7671H.putParcelable(state, "listingRegistrationProcess", target.listingRegistrationProcess);
        f7671H.putParcelable(state, "listing", target.listing);
    }
}
