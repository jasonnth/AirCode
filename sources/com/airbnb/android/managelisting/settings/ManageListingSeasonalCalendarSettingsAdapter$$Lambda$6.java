package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.utils.SeasonalSettingsDisplay;
import p032rx.functions.Func1;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsAdapter$$Lambda$6 implements Func1 {
    private static final ManageListingSeasonalCalendarSettingsAdapter$$Lambda$6 instance = new ManageListingSeasonalCalendarSettingsAdapter$$Lambda$6();

    private ManageListingSeasonalCalendarSettingsAdapter$$Lambda$6() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return Integer.valueOf(SeasonalSettingsDisplay.getDayOfWeekOptionsFromIndex((Integer) obj));
    }
}
