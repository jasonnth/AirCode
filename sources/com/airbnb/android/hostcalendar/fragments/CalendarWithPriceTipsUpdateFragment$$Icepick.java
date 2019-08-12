package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import com.airbnb.android.hostcalendar.fragments.CalendarWithPriceTipsUpdateFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CalendarWithPriceTipsUpdateFragment$$Icepick<T extends CalendarWithPriceTipsUpdateFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9042H = new Helper("com.airbnb.android.hostcalendar.fragments.CalendarWithPriceTipsUpdateFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedDays = f9042H.getParcelableArrayList(state, "selectedDays");
            target.blockedReason = f9042H.getString(state, "blockedReason");
            target.hostNotes = f9042H.getString(state, "hostNotes");
            target.hasLoggedJitneyPriceTipImpression = f9042H.getBoolean(state, "hasLoggedJitneyPriceTipImpression");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9042H.putParcelableArrayList(state, "selectedDays", target.selectedDays);
        f9042H.putString(state, "blockedReason", target.blockedReason);
        f9042H.putString(state, "hostNotes", target.hostNotes);
        f9042H.putBoolean(state, "hasLoggedJitneyPriceTipImpression", target.hasLoggedJitneyPriceTipImpression);
    }
}
