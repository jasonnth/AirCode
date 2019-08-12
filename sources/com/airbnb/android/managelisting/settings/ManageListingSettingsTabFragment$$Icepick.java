package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingSettingsTabFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingSettingsTabFragment$$Icepick<T extends ManageListingSettingsTabFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10187H = new Helper("com.airbnb.android.managelisting.settings.ManageListingSettingsTabFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.initialDataLoaded = f10187H.getBoolean(state, "initialDataLoaded");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10187H.putBoolean(state, "initialDataLoaded", target.initialDataLoaded);
    }
}
