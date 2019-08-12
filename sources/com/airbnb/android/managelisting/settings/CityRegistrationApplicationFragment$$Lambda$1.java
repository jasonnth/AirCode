package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.ListingRegistrationProcessesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CityRegistrationApplicationFragment$$Lambda$1 implements Action1 {
    private final CityRegistrationApplicationFragment arg$1;

    private CityRegistrationApplicationFragment$$Lambda$1(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        this.arg$1 = cityRegistrationApplicationFragment;
    }

    public static Action1 lambdaFactory$(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        return new CityRegistrationApplicationFragment$$Lambda$1(cityRegistrationApplicationFragment);
    }

    public void call(Object obj) {
        CityRegistrationApplicationFragment.lambda$new$0(this.arg$1, (ListingRegistrationProcessesResponse) obj);
    }
}
