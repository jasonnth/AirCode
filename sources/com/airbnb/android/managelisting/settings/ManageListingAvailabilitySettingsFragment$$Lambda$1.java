package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CalendarRulesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAvailabilitySettingsFragment$$Lambda$1 implements Action1 {
    private final ManageListingAvailabilitySettingsFragment arg$1;

    private ManageListingAvailabilitySettingsFragment$$Lambda$1(ManageListingAvailabilitySettingsFragment manageListingAvailabilitySettingsFragment) {
        this.arg$1 = manageListingAvailabilitySettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAvailabilitySettingsFragment manageListingAvailabilitySettingsFragment) {
        return new ManageListingAvailabilitySettingsFragment$$Lambda$1(manageListingAvailabilitySettingsFragment);
    }

    public void call(Object obj) {
        ManageListingAvailabilitySettingsFragment.lambda$new$0(this.arg$1, (CalendarRulesResponse) obj);
    }
}
