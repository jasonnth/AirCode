package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationBaseSubmissionFragment$$Lambda$1 implements Action1 {
    private final CityRegistrationBaseSubmissionFragment arg$1;

    private CityRegistrationBaseSubmissionFragment$$Lambda$1(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        this.arg$1 = cityRegistrationBaseSubmissionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        return new CityRegistrationBaseSubmissionFragment$$Lambda$1(cityRegistrationBaseSubmissionFragment);
    }

    public void call(Object obj) {
        CityRegistrationBaseSubmissionFragment.lambda$new$0(this.arg$1, (ListingRegistrationProcessesResponse) obj);
    }
}
