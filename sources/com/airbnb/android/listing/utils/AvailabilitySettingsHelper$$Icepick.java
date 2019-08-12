package com.airbnb.android.listing.utils;

import android.os.Bundle;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.listing.utils.AvailabilitySettingsHelper;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AvailabilitySettingsHelper$$Icepick<T extends AvailabilitySettingsHelper> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9807H = new Helper("com.airbnb.android.listing.utils.AvailabilitySettingsHelper$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.newSettings = (CalendarRule) f9807H.getParcelable(state, "newSettings");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9807H.putParcelable(state, "newSettings", target.newSettings);
    }
}
