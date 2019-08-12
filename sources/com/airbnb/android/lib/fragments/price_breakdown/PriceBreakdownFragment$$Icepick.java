package com.airbnb.android.lib.fragments.price_breakdown;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.fragments.price_breakdown.PriceBreakdownFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PriceBreakdownFragment$$Icepick<T extends PriceBreakdownFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9581H = new Helper("com.airbnb.android.lib.fragments.price_breakdown.PriceBreakdownFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listing = (Listing) f9581H.getParcelable(state, "listing");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9581H.putParcelable(state, "listing", target.listing);
    }
}
