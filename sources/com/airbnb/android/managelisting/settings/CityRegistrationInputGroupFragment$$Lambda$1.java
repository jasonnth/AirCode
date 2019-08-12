package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter.Listener;

final /* synthetic */ class CityRegistrationInputGroupFragment$$Lambda$1 implements Listener {
    private final CityRegistrationInputGroupFragment arg$1;

    private CityRegistrationInputGroupFragment$$Lambda$1(CityRegistrationInputGroupFragment cityRegistrationInputGroupFragment) {
        this.arg$1 = cityRegistrationInputGroupFragment;
    }

    public static Listener lambdaFactory$(CityRegistrationInputGroupFragment cityRegistrationInputGroupFragment) {
        return new CityRegistrationInputGroupFragment$$Lambda$1(cityRegistrationInputGroupFragment);
    }

    public void enableNextButton(boolean z) {
        this.arg$1.enableNextButton(z);
    }
}
