package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingHouseRulesSettingsFragment$$Lambda$1 implements Action1 {
    private final ManageListingHouseRulesSettingsFragment arg$1;

    private ManageListingHouseRulesSettingsFragment$$Lambda$1(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        this.arg$1 = manageListingHouseRulesSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        return new ManageListingHouseRulesSettingsFragment$$Lambda$1(manageListingHouseRulesSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingHouseRulesSettingsFragment.lambda$new$0(this.arg$1, (ListingResponse) obj);
    }
}
