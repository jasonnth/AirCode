package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationReviewFragment$$Lambda$3 implements Action1 {
    private final LYSCityRegistrationReviewFragment arg$1;

    private LYSCityRegistrationReviewFragment$$Lambda$3(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment) {
        this.arg$1 = lYSCityRegistrationReviewFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationReviewFragment lYSCityRegistrationReviewFragment) {
        return new LYSCityRegistrationReviewFragment$$Lambda$3(lYSCityRegistrationReviewFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
