package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingInstantBookSettingsFragment$$Lambda$2 implements Action1 {
    private final ManageListingInstantBookSettingsFragment arg$1;

    private ManageListingInstantBookSettingsFragment$$Lambda$2(ManageListingInstantBookSettingsFragment manageListingInstantBookSettingsFragment) {
        this.arg$1 = manageListingInstantBookSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingInstantBookSettingsFragment manageListingInstantBookSettingsFragment) {
        return new ManageListingInstantBookSettingsFragment$$Lambda$2(manageListingInstantBookSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingInstantBookSettingsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
