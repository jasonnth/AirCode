package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.listing.adapters.CityRegistrationInputGroupAdapter.Listener;

final /* synthetic */ class LYSCityRegistrationInputGroupFragment$$Lambda$1 implements Listener {
    private final LYSCityRegistrationInputGroupFragment arg$1;

    private LYSCityRegistrationInputGroupFragment$$Lambda$1(LYSCityRegistrationInputGroupFragment lYSCityRegistrationInputGroupFragment) {
        this.arg$1 = lYSCityRegistrationInputGroupFragment;
    }

    public static Listener lambdaFactory$(LYSCityRegistrationInputGroupFragment lYSCityRegistrationInputGroupFragment) {
        return new LYSCityRegistrationInputGroupFragment$$Lambda$1(lYSCityRegistrationInputGroupFragment);
    }

    public void enableNextButton(boolean z) {
        this.arg$1.enableNextButton(z);
    }
}
