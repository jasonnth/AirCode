package com.airbnb.android.listyourspacedls.adapters;

import android.os.Bundle;
import com.airbnb.android.listyourspacedls.adapters.RTBChecklistAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class RTBChecklistAdapter$$Icepick<T extends RTBChecklistAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9923H = new Helper("com.airbnb.android.listyourspacedls.adapters.RTBChecklistAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.responseTimeChecked = f9923H.getBoolean(state, "responseTimeChecked");
            target.searchFilterChecked = f9923H.getBoolean(state, "searchFilterChecked");
            target.lessReservationChecked = f9923H.getBoolean(state, "lessReservationChecked");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9923H.putBoolean(state, "responseTimeChecked", target.responseTimeChecked);
        f9923H.putBoolean(state, "searchFilterChecked", target.searchFilterChecked);
        f9923H.putBoolean(state, "lessReservationChecked", target.lessReservationChecked);
    }
}
