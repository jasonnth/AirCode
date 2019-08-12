package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingBasePriceSettingFragment$$Lambda$2 implements Action1 {
    private final ManageListingBasePriceSettingFragment arg$1;

    private ManageListingBasePriceSettingFragment$$Lambda$2(ManageListingBasePriceSettingFragment manageListingBasePriceSettingFragment) {
        this.arg$1 = manageListingBasePriceSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingBasePriceSettingFragment manageListingBasePriceSettingFragment) {
        return new ManageListingBasePriceSettingFragment$$Lambda$2(manageListingBasePriceSettingFragment);
    }

    public void call(Object obj) {
        ManageListingBasePriceSettingFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
