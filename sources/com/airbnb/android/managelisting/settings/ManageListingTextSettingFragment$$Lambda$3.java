package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.BookingSettingsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingTextSettingFragment$$Lambda$3 implements Action1 {
    private final ManageListingTextSettingFragment arg$1;

    private ManageListingTextSettingFragment$$Lambda$3(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        this.arg$1 = manageListingTextSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        return new ManageListingTextSettingFragment$$Lambda$3(manageListingTextSettingFragment);
    }

    public void call(Object obj) {
        ManageListingTextSettingFragment.lambda$new$1(this.arg$1, (BookingSettingsResponse) obj);
    }
}
