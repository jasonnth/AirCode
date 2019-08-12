package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingTripLengthFragment$$Lambda$1 implements Action1 {
    private final ManageListingTripLengthFragment arg$1;

    private ManageListingTripLengthFragment$$Lambda$1(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        this.arg$1 = manageListingTripLengthFragment;
    }

    public static Action1 lambdaFactory$(ManageListingTripLengthFragment manageListingTripLengthFragment) {
        return new ManageListingTripLengthFragment$$Lambda$1(manageListingTripLengthFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleBatchResponse((AirBatchResponse) obj);
    }
}
