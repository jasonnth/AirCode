package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckInOutFragment$$Lambda$2 implements Action1 {
    private final ManageListingCheckInOutFragment arg$1;

    private ManageListingCheckInOutFragment$$Lambda$2(ManageListingCheckInOutFragment manageListingCheckInOutFragment) {
        this.arg$1 = manageListingCheckInOutFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckInOutFragment manageListingCheckInOutFragment) {
        return new ManageListingCheckInOutFragment$$Lambda$2(manageListingCheckInOutFragment);
    }

    public void call(Object obj) {
        ManageListingCheckInOutFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
