package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.listyourspacedls.fragments.LYSCalendarFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class LYSCalendarFragment$$Icepick<T extends LYSCalendarFragment> extends LYSBaseFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9930H = new Helper("com.airbnb.android.listyourspacedls.fragments.LYSCalendarFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.daysToSetUnavailable = (CalendarDays) f9930H.getParcelable(state, "daysToSetUnavailable");
            target.daysToSetAvailable = (CalendarDays) f9930H.getParcelable(state, "daysToSetAvailable");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9930H.putParcelable(state, "daysToSetUnavailable", target.daysToSetUnavailable);
        f9930H.putParcelable(state, "daysToSetAvailable", target.daysToSetAvailable);
    }
}
