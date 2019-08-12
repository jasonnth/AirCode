package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CalendarRulesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingSeasonalCalendarSettingsFragment$$Lambda$1 implements Action1 {
    private final ManageListingSeasonalCalendarSettingsFragment arg$1;

    private ManageListingSeasonalCalendarSettingsFragment$$Lambda$1(ManageListingSeasonalCalendarSettingsFragment manageListingSeasonalCalendarSettingsFragment) {
        this.arg$1 = manageListingSeasonalCalendarSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingSeasonalCalendarSettingsFragment manageListingSeasonalCalendarSettingsFragment) {
        return new ManageListingSeasonalCalendarSettingsFragment$$Lambda$1(manageListingSeasonalCalendarSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingSeasonalCalendarSettingsFragment.lambda$new$0(this.arg$1, (CalendarRulesResponse) obj);
    }
}
