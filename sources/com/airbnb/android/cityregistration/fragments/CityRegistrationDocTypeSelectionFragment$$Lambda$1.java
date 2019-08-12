package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.cityregistration.adapters.CityRegistrationDocTypeSelectionAdapter.Listener;

final /* synthetic */ class CityRegistrationDocTypeSelectionFragment$$Lambda$1 implements Listener {
    private final CityRegistrationDocTypeSelectionFragment arg$1;

    private CityRegistrationDocTypeSelectionFragment$$Lambda$1(CityRegistrationDocTypeSelectionFragment cityRegistrationDocTypeSelectionFragment) {
        this.arg$1 = cityRegistrationDocTypeSelectionFragment;
    }

    public static Listener lambdaFactory$(CityRegistrationDocTypeSelectionFragment cityRegistrationDocTypeSelectionFragment) {
        return new CityRegistrationDocTypeSelectionFragment$$Lambda$1(cityRegistrationDocTypeSelectionFragment);
    }

    public void getDocPhoto(String str) {
        CityRegistrationDocTypeSelectionFragment.lambda$new$0(this.arg$1, str);
    }
}
