package com.airbnb.android.lib.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.views.GroupedDates;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class GroupedDates$$Icepick<T extends GroupedDates> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9669H = new Helper("com.airbnb.android.lib.views.GroupedDates$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.mCheckInDate = (AirDate) f9669H.getParcelable(state, "mCheckInDate");
        target.mCheckOutDate = (AirDate) f9669H.getParcelable(state, "mCheckOutDate");
        return super.restore(target, f9669H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f9669H.putParent(super.save(target, p));
        f9669H.putParcelable(state, "mCheckInDate", target.mCheckInDate);
        f9669H.putParcelable(state, "mCheckOutDate", target.mCheckOutDate);
        return state;
    }
}
