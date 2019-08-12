package com.airbnb.android.core.fragments.datepicker;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.views.calendar.CalendarView.Style;
import com.facebook.internal.AnalyticsEvents;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DatesFragment$$Icepick<T extends DatesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8454H = new Helper("com.airbnb.android.core.fragments.datepicker.DatesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.startDate = (AirDate) f8454H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f8454H.getParcelable(state, "endDate");
            target.formatWithYear = f8454H.getBoolean(state, "formatWithYear");
            target.allowReset = f8454H.getBoolean(state, "allowReset");
            target.availability = f8454H.getParcelableArrayList(state, "availability");
            target.style = (Style) f8454H.getSerializable(state, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE);
            target.hostName = f8454H.getString(state, "hostName");
            target.minimumNights = f8454H.getInt(state, "minimumNights");
            target.minimumNightsString = f8454H.getString(state, "minimumNightsString");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8454H.putParcelable(state, "startDate", target.startDate);
        f8454H.putParcelable(state, "endDate", target.endDate);
        f8454H.putBoolean(state, "formatWithYear", target.formatWithYear);
        f8454H.putBoolean(state, "allowReset", target.allowReset);
        f8454H.putParcelableArrayList(state, "availability", target.availability);
        f8454H.putSerializable(state, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, target.style);
        f8454H.putString(state, "hostName", target.hostName);
        f8454H.putInt(state, "minimumNights", target.minimumNights);
        f8454H.putString(state, "minimumNightsString", target.minimumNightsString);
    }
}
