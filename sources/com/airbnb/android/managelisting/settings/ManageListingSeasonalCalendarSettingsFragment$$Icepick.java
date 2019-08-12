package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingSeasonalCalendarSettingsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingSeasonalCalendarSettingsFragment$$Icepick<T extends ManageListingSeasonalCalendarSettingsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10185H = new Helper("com.airbnb.android.managelisting.settings.ManageListingSeasonalCalendarSettingsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.allowSettingRemoveButton = f10185H.getBoolean(state, "allowSettingRemoveButton");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10185H.putBoolean(state, "allowSettingRemoveButton", target.allowSettingRemoveButton);
    }
}
