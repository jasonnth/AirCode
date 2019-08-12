package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HouseRulesSettingsAdapter$$Icepick<T extends HouseRulesSettingsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9789H = new Helper("com.airbnb.android.listing.adapters.HouseRulesSettingsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.guestControls = (GuestControls) f9789H.getParcelable(state, "guestControls");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9789H.putParcelable(state, "guestControls", target.guestControls);
    }
}
