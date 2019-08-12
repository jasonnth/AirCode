package com.airbnb.android.places.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.places.adapters.TimeOfDayController.TimeOfDay;
import com.airbnb.android.places.fragments.PickAddToPlansFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PickAddToPlansFragment$$Icepick<T extends PickAddToPlansFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f11165H = new Helper("com.airbnb.android.places.fragments.PickAddToPlansFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedDate = (AirDate) f11165H.getParcelable(state, "selectedDate");
            target.selectedTimeOfDay = (TimeOfDay) f11165H.getSerializable(state, "selectedTimeOfDay");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f11165H.putParcelable(state, "selectedDate", target.selectedDate);
        f11165H.putSerializable(state, "selectedTimeOfDay", target.selectedTimeOfDay);
    }
}
