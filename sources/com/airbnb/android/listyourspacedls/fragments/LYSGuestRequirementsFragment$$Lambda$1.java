package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestRequirementsFragment$$Lambda$1 implements Action1 {
    private final LYSGuestRequirementsFragment arg$1;

    private LYSGuestRequirementsFragment$$Lambda$1(LYSGuestRequirementsFragment lYSGuestRequirementsFragment) {
        this.arg$1 = lYSGuestRequirementsFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestRequirementsFragment lYSGuestRequirementsFragment) {
        return new LYSGuestRequirementsFragment$$Lambda$1(lYSGuestRequirementsFragment);
    }

    public void call(Object obj) {
        this.arg$1.controller.setListing(((SimpleListingResponse) obj).listing);
    }
}
