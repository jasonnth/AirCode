package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SelectListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingTextSettingFragment$$Lambda$5 implements Action1 {
    private final ManageListingTextSettingFragment arg$1;

    private ManageListingTextSettingFragment$$Lambda$5(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        this.arg$1 = manageListingTextSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        return new ManageListingTextSettingFragment$$Lambda$5(manageListingTextSettingFragment);
    }

    public void call(Object obj) {
        ManageListingTextSettingFragment.lambda$new$2(this.arg$1, (SelectListingResponse) obj);
    }
}
