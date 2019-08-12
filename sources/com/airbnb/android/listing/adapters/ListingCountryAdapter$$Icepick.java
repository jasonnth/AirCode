package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.listing.adapters.ListingCountryAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ListingCountryAdapter$$Icepick<T extends ListingCountryAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9792H = new Helper("com.airbnb.android.listing.adapters.ListingCountryAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countries = f9792H.getParcelableArrayList(state, "countries");
            target.currentCountry = (Country) f9792H.getParcelable(state, "currentCountry");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9792H.putParcelableArrayList(state, "countries", target.countries);
        f9792H.putParcelable(state, "currentCountry", target.currentCountry);
    }
}
