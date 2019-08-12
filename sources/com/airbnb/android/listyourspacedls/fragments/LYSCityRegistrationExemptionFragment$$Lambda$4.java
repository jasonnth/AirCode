package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;

final /* synthetic */ class LYSCityRegistrationExemptionFragment$$Lambda$4 implements Listener {
    private final LYSCityRegistrationExemptionFragment arg$1;

    private LYSCityRegistrationExemptionFragment$$Lambda$4(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        this.arg$1 = lYSCityRegistrationExemptionFragment;
    }

    public static Listener lambdaFactory$(LYSCityRegistrationExemptionFragment lYSCityRegistrationExemptionFragment) {
        return new LYSCityRegistrationExemptionFragment$$Lambda$4(lYSCityRegistrationExemptionFragment);
    }

    public void inputIsValid(boolean z) {
        this.arg$1.enableNextButton(z);
    }
}
