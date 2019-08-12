package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationBaseSubmissionFragment$$Lambda$5 implements Action1 {
    private final CityRegistrationBaseSubmissionFragment arg$1;

    private CityRegistrationBaseSubmissionFragment$$Lambda$5(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        this.arg$1 = cityRegistrationBaseSubmissionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        return new CityRegistrationBaseSubmissionFragment$$Lambda$5(cityRegistrationBaseSubmissionFragment);
    }

    public void call(Object obj) {
        CityRegistrationBaseSubmissionFragment.lambda$new$4(this.arg$1, (AirBatchResponse) obj);
    }
}
