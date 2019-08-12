package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationApplicationFragment$$Lambda$3 implements Action1 {
    private final LYSCityRegistrationApplicationFragment arg$1;

    private LYSCityRegistrationApplicationFragment$$Lambda$3(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        this.arg$1 = lYSCityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        return new LYSCityRegistrationApplicationFragment$$Lambda$3(lYSCityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        this.arg$1.setLoadingFinished(((Boolean) obj).booleanValue(), null);
    }
}
