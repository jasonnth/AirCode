package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingNightlyPriceSettingsFragment$$Lambda$1 implements Action1 {
    private final ManageListingNightlyPriceSettingsFragment arg$1;

    private ManageListingNightlyPriceSettingsFragment$$Lambda$1(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment) {
        this.arg$1 = manageListingNightlyPriceSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment) {
        return new ManageListingNightlyPriceSettingsFragment$$Lambda$1(manageListingNightlyPriceSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingNightlyPriceSettingsFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
