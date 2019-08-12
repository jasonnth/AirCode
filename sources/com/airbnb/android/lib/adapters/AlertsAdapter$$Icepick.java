package com.airbnb.android.lib.adapters;

import android.os.Bundle;
import com.airbnb.android.lib.adapters.AlertsAdapter;
import com.airbnb.android.lib.fragments.alerts.AlertsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AlertsAdapter$$Icepick<T extends AlertsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9488H = new Helper("com.airbnb.android.lib.adapters.AlertsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.alerts = f9488H.getParcelableArrayList(state, AlertsFragment.RESULT_UPDATED_ALERTS);
            target.isLoading = f9488H.getBoolean(state, "isLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9488H.putParcelableArrayList(state, AlertsFragment.RESULT_UPDATED_ALERTS, target.alerts);
        f9488H.putBoolean(state, "isLoading", target.isLoading);
    }
}
