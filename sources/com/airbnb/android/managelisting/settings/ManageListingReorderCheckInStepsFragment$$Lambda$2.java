package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingReorderCheckInStepsFragment$$Lambda$2 implements Action1 {
    private final ManageListingReorderCheckInStepsFragment arg$1;

    private ManageListingReorderCheckInStepsFragment$$Lambda$2(ManageListingReorderCheckInStepsFragment manageListingReorderCheckInStepsFragment) {
        this.arg$1 = manageListingReorderCheckInStepsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingReorderCheckInStepsFragment manageListingReorderCheckInStepsFragment) {
        return new ManageListingReorderCheckInStepsFragment$$Lambda$2(manageListingReorderCheckInStepsFragment);
    }

    public void call(Object obj) {
        ManageListingReorderCheckInStepsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
