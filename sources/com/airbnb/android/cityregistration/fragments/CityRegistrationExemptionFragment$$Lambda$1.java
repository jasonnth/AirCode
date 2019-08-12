package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationExemptionFragment$$Lambda$1 implements Action1 {
    private final CityRegistrationExemptionFragment arg$1;

    private CityRegistrationExemptionFragment$$Lambda$1(CityRegistrationExemptionFragment cityRegistrationExemptionFragment) {
        this.arg$1 = cityRegistrationExemptionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationExemptionFragment cityRegistrationExemptionFragment) {
        return new CityRegistrationExemptionFragment$$Lambda$1(cityRegistrationExemptionFragment);
    }

    public void call(Object obj) {
        CityRegistrationExemptionFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
