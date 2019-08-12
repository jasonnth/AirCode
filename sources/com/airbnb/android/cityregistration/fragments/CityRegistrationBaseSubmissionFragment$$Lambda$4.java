package com.airbnb.android.cityregistration.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationBaseSubmissionFragment$$Lambda$4 implements Action1 {
    private final CityRegistrationBaseSubmissionFragment arg$1;

    private CityRegistrationBaseSubmissionFragment$$Lambda$4(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        this.arg$1 = cityRegistrationBaseSubmissionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        return new CityRegistrationBaseSubmissionFragment$$Lambda$4(cityRegistrationBaseSubmissionFragment);
    }

    public void call(Object obj) {
        CityRegistrationBaseSubmissionFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
