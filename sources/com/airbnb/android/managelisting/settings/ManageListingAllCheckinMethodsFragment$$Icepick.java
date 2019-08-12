package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.managelisting.settings.ManageListingAllCheckinMethodsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingAllCheckinMethodsFragment$$Icepick<T extends ManageListingAllCheckinMethodsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10174H = new Helper("com.airbnb.android.managelisting.settings.ManageListingAllCheckinMethodsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.enabled = f10174H.getBoolean(state, "enabled");
            target.changedMethods = (HashMap) f10174H.getSerializable(state, "changedMethods");
            target.selectedMethod = (CheckInInformation) f10174H.getParcelable(state, "selectedMethod");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10174H.putBoolean(state, "enabled", target.enabled);
        f10174H.putSerializable(state, "changedMethods", target.changedMethods);
        f10174H.putParcelable(state, "selectedMethod", target.selectedMethod);
    }
}
