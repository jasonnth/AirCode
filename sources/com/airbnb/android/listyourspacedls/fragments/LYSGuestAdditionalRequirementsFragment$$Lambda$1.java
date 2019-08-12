package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSGuestAdditionalRequirementsFragment$$Lambda$1 implements Action1 {
    private final LYSGuestAdditionalRequirementsFragment arg$1;

    private LYSGuestAdditionalRequirementsFragment$$Lambda$1(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment) {
        this.arg$1 = lYSGuestAdditionalRequirementsFragment;
    }

    public static Action1 lambdaFactory$(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment) {
        return new LYSGuestAdditionalRequirementsFragment$$Lambda$1(lYSGuestAdditionalRequirementsFragment);
    }

    public void call(Object obj) {
        LYSGuestAdditionalRequirementsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
