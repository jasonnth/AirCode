package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsFragment$$Lambda$2 implements Action1 {
    private final ManageListingSeasonalCalendarSettingsFragment arg$1;

    private ManageListingSeasonalCalendarSettingsFragment$$Lambda$2(ManageListingSeasonalCalendarSettingsFragment manageListingSeasonalCalendarSettingsFragment) {
        this.arg$1 = manageListingSeasonalCalendarSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingSeasonalCalendarSettingsFragment manageListingSeasonalCalendarSettingsFragment) {
        return new ManageListingSeasonalCalendarSettingsFragment$$Lambda$2(manageListingSeasonalCalendarSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingSeasonalCalendarSettingsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
