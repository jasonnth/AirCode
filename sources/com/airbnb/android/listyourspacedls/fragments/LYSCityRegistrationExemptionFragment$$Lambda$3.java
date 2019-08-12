package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationExemptionFragment$$Lambda$3 implements Action1 {
    private final LYSCityRegistrationExemptionFragment arg$1;

    private LYSCityRegistrationExemptionFragment$$Lambda$3(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        this.arg$1 = lYSCityRegistrationExemptionFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        return new LYSCityRegistrationExemptionFragment$$Lambda$3(lYSCityRegistrationExemptionFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), this.arg$1.adapter);
    }
}
