package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.hostcalendar.fragments.CalendarFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CalendarFragment$$Icepick<T extends CalendarFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9041H = new Helper("com.airbnb.android.hostcalendar.fragments.CalendarFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listings = f9041H.getParcelableArrayList(state, "listings");
            target.listingLoadedTime = (AirDateTime) f9041H.getParcelable(state, "listingLoadedTime");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9041H.putParcelableArrayList(state, "listings", target.listings);
        f9041H.putParcelable(state, "listingLoadedTime", target.listingLoadedTime);
    }
}
