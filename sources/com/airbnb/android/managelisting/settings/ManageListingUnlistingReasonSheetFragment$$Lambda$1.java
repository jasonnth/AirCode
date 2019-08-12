package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SupportPhoneNumbersResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingUnlistingReasonSheetFragment$$Lambda$1 implements Action1 {
    private final ManageListingUnlistingReasonSheetFragment arg$1;

    private ManageListingUnlistingReasonSheetFragment$$Lambda$1(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        this.arg$1 = manageListingUnlistingReasonSheetFragment;
    }

    public static Action1 lambdaFactory$(ManageListingUnlistingReasonSheetFragment manageListingUnlistingReasonSheetFragment) {
        return new ManageListingUnlistingReasonSheetFragment$$Lambda$1(manageListingUnlistingReasonSheetFragment);
    }

    public void call(Object obj) {
        ManageListingUnlistingReasonSheetFragment.lambda$new$0(this.arg$1, (SupportPhoneNumbersResponse) obj);
    }
}
