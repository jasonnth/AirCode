package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.listing.adapters.LicenseOrRegistrationNumberAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LicenseOrRegistrationNumberAdapter$$Icepick<T extends LicenseOrRegistrationNumberAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9791H = new Helper("com.airbnb.android.listing.adapters.LicenseOrRegistrationNumberAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.license = f9791H.getString(state, ListingRequestConstants.JSON_LICENSE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9791H.putString(state, ListingRequestConstants.JSON_LICENSE_KEY, target.license);
    }
}
