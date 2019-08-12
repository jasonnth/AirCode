package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationReviewFragment$$Lambda$1 implements Action1 {
    private final LYSCityRegistrationReviewFragment arg$1;

    private LYSCityRegistrationReviewFragment$$Lambda$1(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment) {
        this.arg$1 = lYSCityRegistrationReviewFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment) {
        return new LYSCityRegistrationReviewFragment$$Lambda$1(lYSCityRegistrationReviewFragment);
    }

    public void call(Object obj) {
        LYSCityRegistrationReviewFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
