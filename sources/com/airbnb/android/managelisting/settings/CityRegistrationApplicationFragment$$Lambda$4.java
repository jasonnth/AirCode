package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationApplicationFragment$$Lambda$4 implements Action1 {
    private final CityRegistrationApplicationFragment arg$1;

    private CityRegistrationApplicationFragment$$Lambda$4(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        this.arg$1 = cityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        return new CityRegistrationApplicationFragment$$Lambda$4(cityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        CityRegistrationApplicationFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
