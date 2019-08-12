package com.airbnb.android.cityregistration.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationBaseSubmissionFragment$$Lambda$6 implements Action1 {
    private final CityRegistrationBaseSubmissionFragment arg$1;

    private CityRegistrationBaseSubmissionFragment$$Lambda$6(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        this.arg$1 = cityRegistrationBaseSubmissionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationBaseSubmissionFragment cityRegistrationBaseSubmissionFragment) {
        return new CityRegistrationBaseSubmissionFragment$$Lambda$6(cityRegistrationBaseSubmissionFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.recyclerView, (AirRequestNetworkException) obj);
    }
}
