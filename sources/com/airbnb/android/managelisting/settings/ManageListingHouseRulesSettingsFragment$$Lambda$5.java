package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingHouseRulesSettingsFragment$$Lambda$5 implements OnClickListener {
    private final ManageListingHouseRulesSettingsFragment arg$1;

    private ManageListingHouseRulesSettingsFragment$$Lambda$5(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        this.arg$1 = manageListingHouseRulesSettingsFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingHouseRulesSettingsFragment manageListingHouseRulesSettingsFragment) {
        return new ManageListingHouseRulesSettingsFragment$$Lambda$5(manageListingHouseRulesSettingsFragment);
    }

    public void onClick(View view) {
        this.arg$1.executeListingRequest();
    }
}
