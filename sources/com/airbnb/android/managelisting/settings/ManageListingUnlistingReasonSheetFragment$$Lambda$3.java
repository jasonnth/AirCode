package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingUnlistingReasonSheetFragment$$Lambda$3 implements Action1 {
    private final ManageListingUnlistingReasonSheetFragment arg$1;

    private ManageListingUnlistingReasonSheetFragment$$Lambda$3(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        this.arg$1 = manageListingUnlistingReasonSheetFragment;
    }

    public static Action1 lambdaFactory$(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        return new ManageListingUnlistingReasonSheetFragment$$Lambda$3(manageListingUnlistingReasonSheetFragment);
    }

    public void call(Object obj) {
        ManageListingUnlistingReasonSheetFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
