package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationReviewExemptedFragment$$Lambda$1 implements Action1 {
    private final LYSCityRegistrationReviewExemptedFragment arg$1;

    private LYSCityRegistrationReviewExemptedFragment$$Lambda$1(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        this.arg$1 = lYSCityRegistrationReviewExemptedFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        return new LYSCityRegistrationReviewExemptedFragment$$Lambda$1(lYSCityRegistrationReviewExemptedFragment);
    }

    public void call(Object obj) {
        LYSCityRegistrationReviewExemptedFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
