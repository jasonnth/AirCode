package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.GuestAdditionalRequirementsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class GuestAdditionalRequirementsAdapter$$Icepick<T extends GuestAdditionalRequirementsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9788H = new Helper("com.airbnb.android.listing.adapters.GuestAdditionalRequirementsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.governmentIdChecked = f9788H.getBoolean(state, "governmentIdChecked");
            target.hostRecommendationChecked = f9788H.getBoolean(state, "hostRecommendationChecked");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9788H.putBoolean(state, "governmentIdChecked", target.governmentIdChecked);
        f9788H.putBoolean(state, "hostRecommendationChecked", target.hostRecommendationChecked);
    }
}
