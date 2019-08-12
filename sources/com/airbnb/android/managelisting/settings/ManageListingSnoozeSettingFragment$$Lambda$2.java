package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingSnoozeSettingFragment$$Lambda$2 implements Action1 {
    private final ManageListingSnoozeSettingFragment arg$1;

    private ManageListingSnoozeSettingFragment$$Lambda$2(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment) {
        this.arg$1 = manageListingSnoozeSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment) {
        return new ManageListingSnoozeSettingFragment$$Lambda$2(manageListingSnoozeSettingFragment);
    }

    public void call(Object obj) {
        ManageListingSnoozeSettingFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
