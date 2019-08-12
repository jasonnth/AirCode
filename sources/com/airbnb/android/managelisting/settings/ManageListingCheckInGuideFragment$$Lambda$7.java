package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CheckInStepResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$7 implements Action1 {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$7(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$7(manageListingCheckInGuideFragment);
    }

    public void call(Object obj) {
        ManageListingCheckInGuideFragment.lambda$new$4(this.arg$1, (CheckInStepResponse) obj);
    }
}
