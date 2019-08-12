package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsAdapter$$Lambda$7 implements Listener {
    private final ManageListingSeasonalCalendarSettingsAdapter arg$1;

    private ManageListingSeasonalCalendarSettingsAdapter$$Lambda$7(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        this.arg$1 = manageListingSeasonalCalendarSettingsAdapter;
    }

    public static Listener lambdaFactory$(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        return new ManageListingSeasonalCalendarSettingsAdapter$$Lambda$7(manageListingSeasonalCalendarSettingsAdapter);
    }

    public void itemSelected(Object obj) {
        this.arg$1.setCheckIndayIndex(((Integer) obj).intValue());
    }
}
