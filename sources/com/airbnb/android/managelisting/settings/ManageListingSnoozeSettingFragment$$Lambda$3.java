package com.airbnb.android.managelisting.settings;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.managelisting.settings.ManageListingSnoozeSettingAdapter.Listener;

final /* synthetic */ class ManageListingSnoozeSettingFragment$$Lambda$3 implements Listener {
    private final ManageListingSnoozeSettingFragment arg$1;

    private ManageListingSnoozeSettingFragment$$Lambda$3(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment) {
        this.arg$1 = manageListingSnoozeSettingFragment;
    }

    public static Listener lambdaFactory$(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment) {
        return new ManageListingSnoozeSettingFragment$$Lambda$3(manageListingSnoozeSettingFragment);
    }

    public void onDateRangeSelected(AirDate airDate, AirDate airDate2) {
        this.arg$1.handleDateRangeRowSelected(airDate, airDate2);
    }
}
