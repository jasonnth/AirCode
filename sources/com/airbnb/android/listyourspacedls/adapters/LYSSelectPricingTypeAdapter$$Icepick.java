package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.core.enums.ListYourSpacePricingMode;
import com.airbnb.android.listyourspacedls.adapters.LYSSelectPricingTypeAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSSelectPricingTypeAdapter$$Icepick<T extends LYSSelectPricingTypeAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9922H = new Helper("com.airbnb.android.listyourspacedls.adapters.LYSSelectPricingTypeAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.pricingMode = (ListYourSpacePricingMode) f9922H.getParcelable(state, "pricingMode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9922H.putParcelable(state, "pricingMode", target.pricingMode);
    }
}
