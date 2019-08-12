package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckinTypeTextSettingFragment$$Lambda$4 implements Action1 {
    private final ManageListingCheckinTypeTextSettingFragment arg$1;

    private ManageListingCheckinTypeTextSettingFragment$$Lambda$4(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        this.arg$1 = manageListingCheckinTypeTextSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        return new ManageListingCheckinTypeTextSettingFragment$$Lambda$4(manageListingCheckinTypeTextSettingFragment);
    }

    public void call(Object obj) {
        ManageListingCheckinTypeTextSettingFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
