package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingNightlyPriceSettingsFragment$$Lambda$6 implements Action1 {
    private final ManageListingNightlyPriceSettingsFragment arg$1;

    private ManageListingNightlyPriceSettingsFragment$$Lambda$6(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment) {
        this.arg$1 = manageListingNightlyPriceSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment) {
        return new ManageListingNightlyPriceSettingsFragment$$Lambda$6(manageListingNightlyPriceSettingsFragment);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
