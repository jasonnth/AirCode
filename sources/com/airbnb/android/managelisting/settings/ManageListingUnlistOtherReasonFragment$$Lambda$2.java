package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingUnlistOtherReasonFragment$$Lambda$2 implements Action1 {
    private final ManageListingUnlistOtherReasonFragment arg$1;

    private ManageListingUnlistOtherReasonFragment$$Lambda$2(ManageListingUnlistOtherReasonFragment manageListingUnlistOtherReasonFragment) {
        this.arg$1 = manageListingUnlistOtherReasonFragment;
    }

    public static Action1 lambdaFactory$(ManageListingUnlistOtherReasonFragment manageListingUnlistOtherReasonFragment) {
        return new ManageListingUnlistOtherReasonFragment$$Lambda$2(manageListingUnlistOtherReasonFragment);
    }

    public void call(Object obj) {
        ManageListingUnlistOtherReasonFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
