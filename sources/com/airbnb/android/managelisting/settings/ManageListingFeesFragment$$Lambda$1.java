package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingFeesFragment$$Lambda$1 implements Action1 {
    private final ManageListingFeesFragment arg$1;

    private ManageListingFeesFragment$$Lambda$1(ManageListingFeesFragment manageListingFeesFragment) {
        this.arg$1 = manageListingFeesFragment;
    }

    public static Action1 lambdaFactory$(ManageListingFeesFragment manageListingFeesFragment) {
        return new ManageListingFeesFragment$$Lambda$1(manageListingFeesFragment);
    }

    public void call(Object obj) {
        ManageListingFeesFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
