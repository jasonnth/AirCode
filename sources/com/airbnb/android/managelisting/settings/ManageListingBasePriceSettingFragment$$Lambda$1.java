package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingBasePriceSettingFragment$$Lambda$1 implements Action1 {
    private final ManageListingBasePriceSettingFragment arg$1;

    private ManageListingBasePriceSettingFragment$$Lambda$1(ManageListingBasePriceSettingFragment manageListingBasePriceSettingFragment) {
        this.arg$1 = manageListingBasePriceSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingBasePriceSettingFragment manageListingBasePriceSettingFragment) {
        return new ManageListingBasePriceSettingFragment$$Lambda$1(manageListingBasePriceSettingFragment);
    }

    public void call(Object obj) {
        ManageListingBasePriceSettingFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
