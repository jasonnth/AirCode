package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.ListingCheckInInformationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckinTypeTextSettingFragment$$Lambda$3 implements Action1 {
    private final ManageListingCheckinTypeTextSettingFragment arg$1;

    private ManageListingCheckinTypeTextSettingFragment$$Lambda$3(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        this.arg$1 = manageListingCheckinTypeTextSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment) {
        return new ManageListingCheckinTypeTextSettingFragment$$Lambda$3(manageListingCheckinTypeTextSettingFragment);
    }

    public void call(Object obj) {
        ManageListingCheckinTypeTextSettingFragment.lambda$new$2(this.arg$1, (ListingCheckInInformationResponse) obj);
    }
}
