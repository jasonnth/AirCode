package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingExactLocationFragment$$Lambda$2 implements Action1 {
    private final ManageListingExactLocationFragment arg$1;

    private ManageListingExactLocationFragment$$Lambda$2(ManageListingExactLocationFragment manageListingExactLocationFragment) {
        this.arg$1 = manageListingExactLocationFragment;
    }

    public static Action1 lambdaFactory$(ManageListingExactLocationFragment manageListingExactLocationFragment) {
        return new ManageListingExactLocationFragment$$Lambda$2(manageListingExactLocationFragment);
    }

    public void call(Object obj) {
        ManageListingExactLocationFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
