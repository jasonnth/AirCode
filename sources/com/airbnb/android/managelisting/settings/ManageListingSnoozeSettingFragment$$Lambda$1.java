package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingSnoozeSettingFragment$$Lambda$1 implements Action1 {
    private final ManageListingSnoozeSettingFragment arg$1;

    private ManageListingSnoozeSettingFragment$$Lambda$1(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment) {
        this.arg$1 = manageListingSnoozeSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingSnoozeSettingFragment manageListingSnoozeSettingFragment) {
        return new ManageListingSnoozeSettingFragment$$Lambda$1(manageListingSnoozeSettingFragment);
    }

    public void call(Object obj) {
        ManageListingSnoozeSettingFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
