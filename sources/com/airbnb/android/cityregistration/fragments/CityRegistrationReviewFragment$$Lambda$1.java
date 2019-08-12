package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationReviewFragment$$Lambda$1 implements Action1 {
    private final CityRegistrationReviewFragment arg$1;

    private CityRegistrationReviewFragment$$Lambda$1(CityRegistrationReviewFragment cityRegistrationReviewFragment) {
        this.arg$1 = cityRegistrationReviewFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationReviewFragment cityRegistrationReviewFragment) {
        return new CityRegistrationReviewFragment$$Lambda$1(cityRegistrationReviewFragment);
    }

    public void call(Object obj) {
        CityRegistrationReviewFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
