package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationReviewExemptedFragment$$Lambda$3 implements Action1 {
    private final LYSCityRegistrationReviewExemptedFragment arg$1;

    private LYSCityRegistrationReviewExemptedFragment$$Lambda$3(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        this.arg$1 = lYSCityRegistrationReviewExemptedFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        return new LYSCityRegistrationReviewExemptedFragment$$Lambda$3(lYSCityRegistrationReviewExemptedFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
