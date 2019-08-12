package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingInstantBookSettingsFragment$$Lambda$1 implements Action1 {
    private final ManageListingInstantBookSettingsFragment arg$1;

    private ManageListingInstantBookSettingsFragment$$Lambda$1(ManageListingInstantBookSettingsFragment manageListingInstantBookSettingsFragment) {
        this.arg$1 = manageListingInstantBookSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingInstantBookSettingsFragment manageListingInstantBookSettingsFragment) {
        return new ManageListingInstantBookSettingsFragment$$Lambda$1(manageListingInstantBookSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingInstantBookSettingsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
