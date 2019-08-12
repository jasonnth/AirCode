package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingExactLocationFragment$$Lambda$1 implements Action1 {
    private final ManageListingExactLocationFragment arg$1;

    private ManageListingExactLocationFragment$$Lambda$1(ManageListingExactLocationFragment manageListingExactLocationFragment) {
        this.arg$1 = manageListingExactLocationFragment;
    }

    public static Action1 lambdaFactory$(ManageListingExactLocationFragment manageListingExactLocationFragment) {
        return new ManageListingExactLocationFragment$$Lambda$1(manageListingExactLocationFragment);
    }

    public void call(Object obj) {
        ManageListingExactLocationFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
