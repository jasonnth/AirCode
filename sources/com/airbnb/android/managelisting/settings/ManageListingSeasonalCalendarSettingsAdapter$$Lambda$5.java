package com.airbnb.android.managelisting.settings;

import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsAdapter$$Lambda$5 implements Predicate {
    private final ManageListingSeasonalCalendarSettingsAdapter arg$1;

    private ManageListingSeasonalCalendarSettingsAdapter$$Lambda$5(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        this.arg$1 = manageListingSeasonalCalendarSettingsAdapter;
    }

    public static Predicate lambdaFactory$(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        return new ManageListingSeasonalCalendarSettingsAdapter$$Lambda$5(manageListingSeasonalCalendarSettingsAdapter);
    }

    public boolean apply(Object obj) {
        return this.arg$1.isDayOfWeekInRange((Integer) obj);
    }
}
