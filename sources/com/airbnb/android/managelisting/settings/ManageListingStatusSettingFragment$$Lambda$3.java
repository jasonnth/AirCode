package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingStatusSettingFragment$$Lambda$3 implements Action1 {
    private final ManageListingStatusSettingFragment arg$1;

    private ManageListingStatusSettingFragment$$Lambda$3(ManageListingStatusSettingFragment manageListingStatusSettingFragment) {
        this.arg$1 = manageListingStatusSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingStatusSettingFragment manageListingStatusSettingFragment) {
        return new ManageListingStatusSettingFragment$$Lambda$3(manageListingStatusSettingFragment);
    }

    public void call(Object obj) {
        this.arg$1.controller.actionExecutor.listingDeleted();
    }
}
