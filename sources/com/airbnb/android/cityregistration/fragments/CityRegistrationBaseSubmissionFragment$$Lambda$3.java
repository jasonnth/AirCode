package com.airbnb.android.cityregistration.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationBaseSubmissionFragment$$Lambda$3 implements Action1 {
    private final CityRegistrationBaseSubmissionFragment arg$1;

    private CityRegistrationBaseSubmissionFragment$$Lambda$3(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        this.arg$1 = cityRegistrationBaseSubmissionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        return new CityRegistrationBaseSubmissionFragment$$Lambda$3(cityRegistrationBaseSubmissionFragment);
    }

    public void call(Object obj) {
        this.arg$1.fetchListingRegistrationProcesses();
    }
}
