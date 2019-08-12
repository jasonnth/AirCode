package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationExemptionFragment$$Lambda$2 implements Action1 {
    private final CityRegistrationExemptionFragment arg$1;

    private CityRegistrationExemptionFragment$$Lambda$2(CityRegistrationExemptionFragment cityRegistrationExemptionFragment) {
        this.arg$1 = cityRegistrationExemptionFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationExemptionFragment cityRegistrationExemptionFragment) {
        return new CityRegistrationExemptionFragment$$Lambda$2(cityRegistrationExemptionFragment);
    }

    public void call(Object obj) {
        CityRegistrationExemptionFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
