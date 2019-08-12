package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingWirelessInfoFragment$$Lambda$3 implements Action1 {
    private final ManageListingWirelessInfoFragment arg$1;

    private ManageListingWirelessInfoFragment$$Lambda$3(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment) {
        this.arg$1 = manageListingWirelessInfoFragment;
    }

    public static Action1 lambdaFactory$(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment) {
        return new ManageListingWirelessInfoFragment$$Lambda$3(manageListingWirelessInfoFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleWifiInfoUpdateResult(null);
    }
}
