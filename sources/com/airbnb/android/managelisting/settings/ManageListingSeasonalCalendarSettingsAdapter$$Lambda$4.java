package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsAdapter$$Lambda$4 implements OnClickListener {
    private final ManageListingSeasonalCalendarSettingsAdapter arg$1;

    private ManageListingSeasonalCalendarSettingsAdapter$$Lambda$4(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        this.arg$1 = manageListingSeasonalCalendarSettingsAdapter;
    }

    public static OnClickListener lambdaFactory$(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        return new ManageListingSeasonalCalendarSettingsAdapter$$Lambda$4(manageListingSeasonalCalendarSettingsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.showCheckInDayOptions();
    }
}
