package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.BasePriceAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BasePriceAdapter$$Icepick<T extends BasePriceAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9784H = new Helper("com.airbnb.android.listing.adapters.BasePriceAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.basePrice = f9784H.getBoxedInt(state, "basePrice");
            target.currencyCode = f9784H.getString(state, "currencyCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9784H.putBoxedInt(state, "basePrice", target.basePrice);
        f9784H.putString(state, "currencyCode", target.currencyCode);
    }
}
