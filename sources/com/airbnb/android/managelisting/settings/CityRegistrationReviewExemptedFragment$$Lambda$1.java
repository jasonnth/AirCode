package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationReviewExemptedFragment$$Lambda$1 implements Action1 {
    private final CityRegistrationReviewExemptedFragment arg$1;

    private CityRegistrationReviewExemptedFragment$$Lambda$1(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment) {
        this.arg$1 = cityRegistrationReviewExemptedFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment) {
        return new CityRegistrationReviewExemptedFragment$$Lambda$1(cityRegistrationReviewExemptedFragment);
    }

    public void call(Object obj) {
        CityRegistrationReviewExemptedFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
