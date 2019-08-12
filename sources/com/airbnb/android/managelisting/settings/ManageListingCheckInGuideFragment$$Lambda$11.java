package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckInGuideFragment$$Lambda$11 implements Action1 {
    private final ManageListingCheckInGuideFragment arg$1;

    private ManageListingCheckInGuideFragment$$Lambda$11(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        this.arg$1 = manageListingCheckInGuideFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment) {
        return new ManageListingCheckInGuideFragment$$Lambda$11(manageListingCheckInGuideFragment);
    }

    public void call(Object obj) {
        this.arg$1.showFullscreenLoader(false);
    }
}
