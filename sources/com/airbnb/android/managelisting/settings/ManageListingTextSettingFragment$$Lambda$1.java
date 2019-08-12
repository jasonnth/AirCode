package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingTextSettingFragment$$Lambda$1 implements Action1 {
    private final ManageListingTextSettingFragment arg$1;

    private ManageListingTextSettingFragment$$Lambda$1(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        this.arg$1 = manageListingTextSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        return new ManageListingTextSettingFragment$$Lambda$1(manageListingTextSettingFragment);
    }

    public void call(Object obj) {
        ManageListingTextSettingFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
