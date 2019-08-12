package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingWirelessInfoFragment$$Lambda$4 implements Action1 {
    private final ManageListingWirelessInfoFragment arg$1;

    private ManageListingWirelessInfoFragment$$Lambda$4(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment) {
        this.arg$1 = manageListingWirelessInfoFragment;
    }

    public static Action1 lambdaFactory$(ManageListingWirelessInfoFragment manageListingWirelessInfoFragment) {
        return new ManageListingWirelessInfoFragment$$Lambda$4(manageListingWirelessInfoFragment);
    }

    public void call(Object obj) {
        ManageListingWirelessInfoFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
