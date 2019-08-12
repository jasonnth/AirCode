package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationApplicationFragment$$Lambda$3 implements Action1 {
    private final CityRegistrationApplicationFragment arg$1;

    private CityRegistrationApplicationFragment$$Lambda$3(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        this.arg$1 = cityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        return new CityRegistrationApplicationFragment$$Lambda$3(cityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        this.arg$1.fetchListingRegistrationProcesses();
    }
}
