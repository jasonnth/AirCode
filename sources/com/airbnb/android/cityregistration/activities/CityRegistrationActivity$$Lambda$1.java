package com.airbnb.android.cityregistration.activities;

import com.airbnb.android.core.responses.ListingRegistrationOpenSubmissionResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationActivity$$Lambda$1 implements Action1 {
    private final CityRegistrationActivity arg$1;

    private CityRegistrationActivity$$Lambda$1(CityRegistrationActivity cityRegistrationActivity) {
        this.arg$1 = cityRegistrationActivity;
    }

    public static Action1 lambdaFactory$(CityRegistrationActivity cityRegistrationActivity) {
        return new CityRegistrationActivity$$Lambda$1(cityRegistrationActivity);
    }

    public void call(Object obj) {
        CityRegistrationActivity.lambda$new$1(this.arg$1, (ListingRegistrationOpenSubmissionResponse) obj);
    }
}
