package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class ManageListingTextSettingFragment$$Lambda$7 implements Listener {
    private final ManageListingTextSettingFragment arg$1;

    private ManageListingTextSettingFragment$$Lambda$7(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        this.arg$1 = manageListingTextSettingFragment;
    }

    public static Listener lambdaFactory$(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        return new ManageListingTextSettingFragment$$Lambda$7(manageListingTextSettingFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.updateSaveButton(z);
    }
}
