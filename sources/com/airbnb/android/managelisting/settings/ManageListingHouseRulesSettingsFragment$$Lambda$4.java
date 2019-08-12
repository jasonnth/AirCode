package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingHouseRulesSettingsFragment$$Lambda$4 implements Action1 {
    private final ManageListingHouseRulesSettingsFragment arg$1;

    private ManageListingHouseRulesSettingsFragment$$Lambda$4(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        this.arg$1 = manageListingHouseRulesSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        return new ManageListingHouseRulesSettingsFragment$$Lambda$4(manageListingHouseRulesSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingHouseRulesSettingsFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
