package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingTripLengthFragment$$Lambda$2 implements Action1 {
    private final ManageListingTripLengthFragment arg$1;

    private ManageListingTripLengthFragment$$Lambda$2(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        this.arg$1 = manageListingTripLengthFragment;
    }

    public static Action1 lambdaFactory$(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        return new ManageListingTripLengthFragment$$Lambda$2(manageListingTripLengthFragment);
    }

    public void call(Object obj) {
        ManageListingTripLengthFragment.lambda$new$0(this.arg$1, (AirRequestNetworkException) obj);
    }
}
