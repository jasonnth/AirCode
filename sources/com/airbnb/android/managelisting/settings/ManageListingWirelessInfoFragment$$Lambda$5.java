package com.airbnb.android.managelisting.settings;

import com.airbnb.android.managelisting.settings.ManageListingWirelessInfoAdapter.OnValidInputListener;

final /* synthetic */ class ManageListingWirelessInfoFragment$$Lambda$5 implements OnValidInputListener {
    private final ManageListingWirelessInfoFragment arg$1;

    private ManageListingWirelessInfoFragment$$Lambda$5(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment) {
        this.arg$1 = manageListingWirelessInfoFragment;
    }

    public static OnValidInputListener lambdaFactory$(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment) {
        return new ManageListingWirelessInfoFragment$$Lambda$5(manageListingWirelessInfoFragment);
    }

    public void onInputChanged(boolean z) {
        this.arg$1.saveButton.setEnabled(z);
    }
}
