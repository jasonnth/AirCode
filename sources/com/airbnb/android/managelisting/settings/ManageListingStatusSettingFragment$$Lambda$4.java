package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingStatusSettingFragment$$Lambda$4 implements Action1 {
    private final ManageListingStatusSettingFragment arg$1;

    private ManageListingStatusSettingFragment$$Lambda$4(ManageListingStatusSettingFragment manageListingStatusSettingFragment) {
        this.arg$1 = manageListingStatusSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingStatusSettingFragment manageListingStatusSettingFragment) {
        return new ManageListingStatusSettingFragment$$Lambda$4(manageListingStatusSettingFragment);
    }

    public void call(Object obj) {
        ManageListingStatusSettingFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
