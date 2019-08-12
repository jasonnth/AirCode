package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationExemptionFragment$$Lambda$1 implements Action1 {
    private final LYSCityRegistrationExemptionFragment arg$1;

    private LYSCityRegistrationExemptionFragment$$Lambda$1(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        this.arg$1 = lYSCityRegistrationExemptionFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        return new LYSCityRegistrationExemptionFragment$$Lambda$1(lYSCityRegistrationExemptionFragment);
    }

    public void call(Object obj) {
        LYSCityRegistrationExemptionFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
