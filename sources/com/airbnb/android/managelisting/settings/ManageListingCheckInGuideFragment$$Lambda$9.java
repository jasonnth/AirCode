package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CheckInStepResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$9 implements Action1 {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$9(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$9(manageListingCheckInGuideFragment);
    }

    public void call(Object obj) {
        ManageListingCheckInGuideFragment.lambda$new$6(this.arg$1, (CheckInStepResponse) obj);
    }
}
