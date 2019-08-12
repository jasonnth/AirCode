package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingStatusSettingFragment$$Lambda$1 implements Action1 {
    private final ManageListingStatusSettingFragment arg$1;

    private ManageListingStatusSettingFragment$$Lambda$1(ManageListingStatusSettingFragment manageListingStatusSettingFragment) {
        this.arg$1 = manageListingStatusSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingStatusSettingFragment manageListingStatusSettingFragment) {
        return new ManageListingStatusSettingFragment$$Lambda$1(manageListingStatusSettingFragment);
    }

    public void call(Object obj) {
        ManageListingStatusSettingFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
