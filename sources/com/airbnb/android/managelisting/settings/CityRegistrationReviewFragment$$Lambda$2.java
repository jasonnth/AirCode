package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationReviewFragment$$Lambda$2 implements Action1 {
    private final CityRegistrationReviewFragment arg$1;

    private CityRegistrationReviewFragment$$Lambda$2(CityRegistrationReviewFragment cityRegistrationReviewFragment) {
        this.arg$1 = cityRegistrationReviewFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationReviewFragment cityRegistrationReviewFragment) {
        return new CityRegistrationReviewFragment$$Lambda$2(cityRegistrationReviewFragment);
    }

    public void call(Object obj) {
        CityRegistrationReviewFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
