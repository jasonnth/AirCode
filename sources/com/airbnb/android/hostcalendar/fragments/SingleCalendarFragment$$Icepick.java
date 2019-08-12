package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SingleCalendarFragment$$Icepick<T extends SingleCalendarFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9045H = new Helper("com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isNavFromMultical = f9045H.getBoolean(state, "isNavFromMultical");
            target.listingId = f9045H.getLong(state, "listingId");
            target.selectedDays = (CalendarDays) f9045H.getParcelable(state, "selectedDays");
            target.calendarRule = (CalendarRule) f9045H.getParcelable(state, "calendarRule");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9045H.putBoolean(state, "isNavFromMultical", target.isNavFromMultical);
        f9045H.putLong(state, "listingId", target.listingId);
        f9045H.putParcelable(state, "selectedDays", target.selectedDays);
        f9045H.putParcelable(state, "calendarRule", target.calendarRule);
    }
}
