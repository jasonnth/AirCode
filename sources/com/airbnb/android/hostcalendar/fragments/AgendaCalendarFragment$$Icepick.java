package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import com.airbnb.android.hostcalendar.fragments.AgendaCalendarFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AgendaCalendarFragment$$Icepick<T extends AgendaCalendarFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9040H = new Helper("com.airbnb.android.hostcalendar.fragments.AgendaCalendarFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.eligibleForNestedListings = f9040H.getBoolean(state, "eligibleForNestedListings");
            target.thumbnailListings = f9040H.getParcelableArrayList(state, "thumbnailListings");
            target.initialAgendaReservations = f9040H.getParcelableArrayList(state, "initialAgendaReservations");
            target.pageLoaded = f9040H.getBoolean(state, "pageLoaded");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9040H.putBoolean(state, "eligibleForNestedListings", target.eligibleForNestedListings);
        f9040H.putParcelableArrayList(state, "thumbnailListings", target.thumbnailListings);
        f9040H.putParcelableArrayList(state, "initialAgendaReservations", target.initialAgendaReservations);
        f9040H.putBoolean(state, "pageLoaded", target.pageLoaded);
    }
}
