package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.listing.adapters.ListingRegistrationAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ListingRegistrationAdapter$$Icepick<T extends ListingRegistrationAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9794H = new Helper("com.airbnb.android.listing.adapters.ListingRegistrationAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.license = f9794H.getString(state, ListingRequestConstants.JSON_LICENSE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9794H.putString(state, ListingRequestConstants.JSON_LICENSE_KEY, target.license);
    }
}
