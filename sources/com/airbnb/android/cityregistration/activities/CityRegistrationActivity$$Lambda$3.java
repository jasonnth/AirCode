package com.airbnb.android.cityregistration.activities;

import com.airbnb.android.core.interfaces.OnHomeListener;

final /* synthetic */ class CityRegistrationActivity$$Lambda$3 implements OnHomeListener {
    private final CityRegistrationActivity arg$1;

    private CityRegistrationActivity$$Lambda$3(CityRegistrationActivity cityRegistrationActivity) {
        this.arg$1 = cityRegistrationActivity;
    }

    public static OnHomeListener lambdaFactory$(CityRegistrationActivity cityRegistrationActivity) {
        return new CityRegistrationActivity$$Lambda$3(cityRegistrationActivity);
    }

    public boolean onHomePressed() {
        return this.arg$1.onBackPressed();
    }
}
