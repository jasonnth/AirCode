package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class ManageListingCheckinTypeTextSettingFragment$$Lambda$5 implements Listener {
    private final ManageListingCheckinTypeTextSettingFragment arg$1;

    private ManageListingCheckinTypeTextSettingFragment$$Lambda$5(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        this.arg$1 = manageListingCheckinTypeTextSettingFragment;
    }

    public static Listener lambdaFactory$(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        return new ManageListingCheckinTypeTextSettingFragment$$Lambda$5(manageListingCheckinTypeTextSettingFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.updateSaveButton(z);
    }
}
