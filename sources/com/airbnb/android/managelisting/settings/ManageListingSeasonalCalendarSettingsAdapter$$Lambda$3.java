package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsAdapter$$Lambda$3 implements OnCheckedChangeListener {
    private final ManageListingSeasonalCalendarSettingsAdapter arg$1;

    private ManageListingSeasonalCalendarSettingsAdapter$$Lambda$3(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        this.arg$1 = manageListingSeasonalCalendarSettingsAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        return new ManageListingSeasonalCalendarSettingsAdapter$$Lambda$3(manageListingSeasonalCalendarSettingsAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.onSpecifiedDayCheckedChanged(z);
    }
}
