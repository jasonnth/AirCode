package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.NotificationSettingsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class NotificationSettingsFragment$$Icepick<T extends NotificationSettingsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9540H = new Helper("com.airbnb.android.lib.fragments.NotificationSettingsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.notificationPreferences = f9540H.getParcelableArrayList(state, "notificationPreferences");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9540H.putParcelableArrayList(state, "notificationPreferences", target.notificationPreferences);
    }
}
