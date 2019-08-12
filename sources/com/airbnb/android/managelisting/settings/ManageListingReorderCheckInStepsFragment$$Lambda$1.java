package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CheckInGuideResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingReorderCheckInStepsFragment$$Lambda$1 implements Action1 {
    private final ManageListingReorderCheckInStepsFragment arg$1;

    private ManageListingReorderCheckInStepsFragment$$Lambda$1(ManageListingReorderCheckInStepsFragment manageListingReorderCheckInStepsFragment) {
        this.arg$1 = manageListingReorderCheckInStepsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingReorderCheckInStepsFragment manageListingReorderCheckInStepsFragment) {
        return new ManageListingReorderCheckInStepsFragment$$Lambda$1(manageListingReorderCheckInStepsFragment);
    }

    public void call(Object obj) {
        ManageListingReorderCheckInStepsFragment.lambda$new$0(this.arg$1, (CheckInGuideResponse) obj);
    }
}
