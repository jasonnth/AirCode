package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;

final /* synthetic */ class CityRegistrationExemptionFragment$$Lambda$3 implements Listener {
    private final CityRegistrationExemptionFragment arg$1;

    private CityRegistrationExemptionFragment$$Lambda$3(CityRegistrationExemptionFragment cityRegistrationExemptionFragment) {
        this.arg$1 = cityRegistrationExemptionFragment;
    }

    public static Listener lambdaFactory$(CityRegistrationExemptionFragment cityRegistrationExemptionFragment) {
        return new CityRegistrationExemptionFragment$$Lambda$3(cityRegistrationExemptionFragment);
    }

    public void inputIsValid(boolean z) {
        this.arg$1.enableSaveButton(z);
    }
}
