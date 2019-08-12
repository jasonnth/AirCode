package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsAdapter$$Lambda$2 implements Listener {
    private final ManageListingSeasonalCalendarSettingsAdapter arg$1;

    private ManageListingSeasonalCalendarSettingsAdapter$$Lambda$2(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        this.arg$1 = manageListingSeasonalCalendarSettingsAdapter;
    }

    public static Listener lambdaFactory$(ManageListingSeasonalCalendarSettingsAdapter manageListingSeasonalCalendarSettingsAdapter) {
        return new ManageListingSeasonalCalendarSettingsAdapter$$Lambda$2(manageListingSeasonalCalendarSettingsAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.notifyListenerForValidInputChanged();
    }
}
