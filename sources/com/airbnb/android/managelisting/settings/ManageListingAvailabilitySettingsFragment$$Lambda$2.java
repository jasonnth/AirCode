package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAvailabilitySettingsFragment$$Lambda$2 implements Action1 {
    private final ManageListingAvailabilitySettingsFragment arg$1;

    private ManageListingAvailabilitySettingsFragment$$Lambda$2(ManageListingAvailabilitySettingsFragment manageListingAvailabilitySettingsFragment) {
        this.arg$1 = manageListingAvailabilitySettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAvailabilitySettingsFragment manageListingAvailabilitySettingsFragment) {
        return new ManageListingAvailabilitySettingsFragment$$Lambda$2(manageListingAvailabilitySettingsFragment);
    }

    public void call(Object obj) {
        ManageListingAvailabilitySettingsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}