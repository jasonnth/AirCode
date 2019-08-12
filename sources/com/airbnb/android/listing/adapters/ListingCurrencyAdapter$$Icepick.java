package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.ListingCurrencyAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ListingCurrencyAdapter$$Icepick<T extends ListingCurrencyAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9793H = new Helper("com.airbnb.android.listing.adapters.ListingCurrencyAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currencies = f9793H.getParcelableArrayList(state, "currencies");
            target.currentCurrencyCode = f9793H.getString(state, "currentCurrencyCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9793H.putParcelableArrayList(state, "currencies", target.currencies);
        f9793H.putString(state, "currentCurrencyCode", target.currentCurrencyCode);
    }
}
