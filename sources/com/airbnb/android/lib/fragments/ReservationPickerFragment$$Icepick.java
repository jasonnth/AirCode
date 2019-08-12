package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.lib.fragments.ReservationPickerFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationPickerFragment$$Icepick<T extends ReservationPickerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9551H = new Helper("com.airbnb.android.lib.fragments.ReservationPickerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.reservations = f9551H.getParcelableArrayList(state, NotificationPreferencesGroups.RESERVATIONS);
            target.thread = (Thread) f9551H.getParcelable(state, ThreadBlockActivityIntents.ARG_THREAD);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9551H.putParcelableArrayList(state, NotificationPreferencesGroups.RESERVATIONS, target.reservations);
        f9551H.putParcelable(state, ThreadBlockActivityIntents.ARG_THREAD, target.thread);
    }
}
