package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingFeesFragment$$Lambda$2 implements Action1 {
    private final ManageListingFeesFragment arg$1;

    private ManageListingFeesFragment$$Lambda$2(ManageListingFeesFragment manageListingFeesFragment) {
        this.arg$1 = manageListingFeesFragment;
    }

    public static Action1 lambdaFactory$(ManageListingFeesFragment manageListingFeesFragment) {
        return new ManageListingFeesFragment$$Lambda$2(manageListingFeesFragment);
    }

    public void call(Object obj) {
        ManageListingFeesFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
