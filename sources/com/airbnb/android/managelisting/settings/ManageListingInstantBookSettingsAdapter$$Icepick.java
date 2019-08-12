package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.managelisting.settings.ManageListingInstantBookSettingsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingInstantBookSettingsAdapter$$Icepick<T extends ManageListingInstantBookSettingsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10181H = new Helper("com.airbnb.android.managelisting.settings.ManageListingInstantBookSettingsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.instantBookingCategory = (InstantBookingAllowedCategory) f10181H.getSerializable(state, "instantBookingCategory");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10181H.putSerializable(state, "instantBookingCategory", target.instantBookingCategory);
    }
}
