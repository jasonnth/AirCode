package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAmenitiesFragment$$Lambda$2 implements Action1 {
    private final ManageListingAmenitiesFragment arg$1;

    private ManageListingAmenitiesFragment$$Lambda$2(ManageListingAmenitiesFragment manageListingAmenitiesFragment) {
        this.arg$1 = manageListingAmenitiesFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAmenitiesFragment manageListingAmenitiesFragment) {
        return new ManageListingAmenitiesFragment$$Lambda$2(manageListingAmenitiesFragment);
    }

    public void call(Object obj) {
        ManageListingAmenitiesFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
