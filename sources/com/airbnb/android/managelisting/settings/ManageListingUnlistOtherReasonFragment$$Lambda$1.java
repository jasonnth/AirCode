package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingUnlistOtherReasonFragment$$Lambda$1 implements Action1 {
    private final ManageListingUnlistOtherReasonFragment arg$1;

    private ManageListingUnlistOtherReasonFragment$$Lambda$1(ManageListingUnlistOtherReasonFragment manageListingUnlistOtherReasonFragment) {
        this.arg$1 = manageListingUnlistOtherReasonFragment;
    }

    public static Action1 lambdaFactory$(ManageListingUnlistOtherReasonFragment manageListingUnlistOtherReasonFragment) {
        return new ManageListingUnlistOtherReasonFragment$$Lambda$1(manageListingUnlistOtherReasonFragment);
    }

    public void call(Object obj) {
        ManageListingUnlistOtherReasonFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
