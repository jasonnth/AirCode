package com.airbnb.android.cityregistration.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CityRegistrationActivity$$Lambda$4 implements OnClickListener {
    private final CityRegistrationActivity arg$1;

    private CityRegistrationActivity$$Lambda$4(CityRegistrationActivity cityRegistrationActivity) {
        this.arg$1 = cityRegistrationActivity;
    }

    public static OnClickListener lambdaFactory$(CityRegistrationActivity cityRegistrationActivity) {
        return new CityRegistrationActivity$$Lambda$4(cityRegistrationActivity);
    }

    public void onClick(View view) {
        this.arg$1.updateListingRegistrationOpenSubmission(this.arg$1.answers);
    }
}
