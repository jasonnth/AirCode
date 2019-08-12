package com.airbnb.android.cityregistration.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationActivity$$Lambda$2 implements Action1 {
    private final CityRegistrationActivity arg$1;

    private CityRegistrationActivity$$Lambda$2(CityRegistrationActivity cityRegistrationActivity) {
        this.arg$1 = cityRegistrationActivity;
    }

    public static Action1 lambdaFactory$(CityRegistrationActivity cityRegistrationActivity) {
        return new CityRegistrationActivity$$Lambda$2(cityRegistrationActivity);
    }

    public void call(Object obj) {
        CityRegistrationActivity.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
