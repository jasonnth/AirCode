package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.managelisting.settings.ManageListingSeasonalCalendarSettingsAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingSeasonalCalendarSettingsAdapter$$Icepick<T extends ManageListingSeasonalCalendarSettingsAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10184H = new Helper("com.airbnb.android.managelisting.settings.ManageListingSeasonalCalendarSettingsAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkInDay = f10184H.getBoxedInt(state, "checkInDay");
            target.minimumNights = f10184H.getBoxedInt(state, "minimumNights");
            target.startDate = (AirDate) f10184H.getParcelable(state, "startDate");
            target.endDate = (AirDate) f10184H.getParcelable(state, "endDate");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10184H.putBoxedInt(state, "checkInDay", target.checkInDay);
        f10184H.putBoxedInt(state, "minimumNights", target.minimumNights);
        f10184H.putParcelable(state, "startDate", target.startDate);
        f10184H.putParcelable(state, "endDate", target.endDate);
    }
}
