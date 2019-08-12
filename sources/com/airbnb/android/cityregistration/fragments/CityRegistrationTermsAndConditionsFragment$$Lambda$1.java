package com.airbnb.android.cityregistration.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CityRegistrationTermsAndConditionsFragment$$Lambda$1 implements OnClickListener {
    private final CityRegistrationTermsAndConditionsFragment arg$1;

    private CityRegistrationTermsAndConditionsFragment$$Lambda$1(CityRegistrationTermsAndConditionsFragment cityRegistrationTermsAndConditionsFragment) {
        this.arg$1 = cityRegistrationTermsAndConditionsFragment;
    }

    public static OnClickListener lambdaFactory$(CityRegistrationTermsAndConditionsFragment cityRegistrationTermsAndConditionsFragment) {
        return new CityRegistrationTermsAndConditionsFragment$$Lambda$1(cityRegistrationTermsAndConditionsFragment);
    }

    public void onClick(View view) {
        CityRegistrationTermsAndConditionsFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
