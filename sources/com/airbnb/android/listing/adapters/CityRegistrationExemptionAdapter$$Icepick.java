package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CityRegistrationExemptionAdapter$$Icepick<T extends CityRegistrationExemptionAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9785H = new Helper("com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.license = f9785H.getString(state, ListingRequestConstants.JSON_LICENSE_KEY);
            target.expiryDate = f9785H.getString(state, "expiryDate");
            target.zipCode = f9785H.getString(state, "zipCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9785H.putString(state, ListingRequestConstants.JSON_LICENSE_KEY, target.license);
        f9785H.putString(state, "expiryDate", target.expiryDate);
        f9785H.putString(state, "zipCode", target.zipCode);
    }
}
