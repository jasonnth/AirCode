package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationApplicationFragment$$Lambda$5 implements Action1 {
    private final LYSCityRegistrationApplicationFragment arg$1;

    private LYSCityRegistrationApplicationFragment$$Lambda$5(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        this.arg$1 = lYSCityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        return new LYSCityRegistrationApplicationFragment$$Lambda$5(lYSCityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        LYSCityRegistrationApplicationFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
