package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarBaseFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SingleCalendarBaseFragment$$Icepick<T extends SingleCalendarBaseFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9043H = new Helper("com.airbnb.android.hostcalendar.fragments.SingleCalendarBaseFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasDoneInitialScroll = f9043H.getBoolean(state, "hasDoneInitialScroll");
            target.startDate = (AirDate) f9043H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f9043H.getParcelable(state, "endDate");
            target.targetScrollDate = (AirDate) f9043H.getParcelable(state, "targetScrollDate");
            target.listingId = f9043H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9043H.putBoolean(state, "hasDoneInitialScroll", target.hasDoneInitialScroll);
        f9043H.putParcelable(state, "startDate", target.startDate);
        f9043H.putParcelable(state, "endDate", target.endDate);
        f9043H.putParcelable(state, "targetScrollDate", target.targetScrollDate);
        f9043H.putLong(state, "listingId", target.listingId);
    }
}
