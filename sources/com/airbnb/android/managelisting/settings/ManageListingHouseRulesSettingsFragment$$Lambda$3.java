package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingHouseRulesSettingsFragment$$Lambda$3 implements Action1 {
    private final ManageListingHouseRulesSettingsFragment arg$1;

    private ManageListingHouseRulesSettingsFragment$$Lambda$3(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        this.arg$1 = manageListingHouseRulesSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        return new ManageListingHouseRulesSettingsFragment$$Lambda$3(manageListingHouseRulesSettingsFragment);
    }

    public void call(Object obj) {
        this.arg$1.executeListingRequest();
    }
}
