package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingNightlyPriceSettingsFragment$$Lambda$3 implements Action1 {
    private final ManageListingNightlyPriceSettingsFragment arg$1;

    private ManageListingNightlyPriceSettingsFragment$$Lambda$3(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment) {
        this.arg$1 = manageListingNightlyPriceSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment) {
        return new ManageListingNightlyPriceSettingsFragment$$Lambda$3(manageListingNightlyPriceSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingNightlyPriceSettingsFragment.lambda$new$1(this.arg$1, (DemandBasedPricingResponse) obj);
    }
}
