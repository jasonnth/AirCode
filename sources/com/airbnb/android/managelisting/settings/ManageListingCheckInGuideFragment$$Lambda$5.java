package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CheckInGuideResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$5 implements Action1 {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$5(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$5(manageListingCheckInGuideFragment);
    }

    public void call(Object obj) {
        ManageListingCheckInGuideFragment.lambda$new$2(this.arg$1, (CheckInGuideResponse) obj);
    }
}
