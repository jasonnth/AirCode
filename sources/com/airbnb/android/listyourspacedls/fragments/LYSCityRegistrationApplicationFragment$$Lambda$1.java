package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCityRegistrationApplicationFragment$$Lambda$1 implements Action1 {
    private final LYSCityRegistrationApplicationFragment arg$1;

    private LYSCityRegistrationApplicationFragment$$Lambda$1(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        this.arg$1 = lYSCityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(LYSCityRegistrationApplicationFragment lYSCityRegistrationApplicationFragment) {
        return new LYSCityRegistrationApplicationFragment$$Lambda$1(lYSCityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        LYSCityRegistrationApplicationFragment.lambda$new$0(this.arg$1, (ListingRegistrationProcessesResponse) obj);
    }
}
