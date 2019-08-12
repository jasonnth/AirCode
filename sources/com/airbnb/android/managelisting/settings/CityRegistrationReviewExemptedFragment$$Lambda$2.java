package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationReviewExemptedFragment$$Lambda$2 implements Action1 {
    private final CityRegistrationReviewExemptedFragment arg$1;

    private CityRegistrationReviewExemptedFragment$$Lambda$2(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment) {
        this.arg$1 = cityRegistrationReviewExemptedFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment) {
        return new CityRegistrationReviewExemptedFragment$$Lambda$2(cityRegistrationReviewExemptedFragment);
    }

    public void call(Object obj) {
        CityRegistrationReviewExemptedFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
