package com.airbnb.android.hostcalendar.activities;

import android.os.Bundle;
import com.airbnb.android.hostcalendar.activities.HostCalendarUpdateActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostCalendarUpdateActivity$$Icepick<T extends HostCalendarUpdateActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9038H = new Helper("com.airbnb.android.hostcalendar.activities.HostCalendarUpdateActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingId = f9038H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9038H.putLong(state, "listingId", target.listingId);
    }
}
