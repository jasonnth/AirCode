package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;

final /* synthetic */ class CityRegistrationReviewExemptedFragment$$Lambda$3 implements Listener {
    private final CityRegistrationReviewExemptedFragment arg$1;

    private CityRegistrationReviewExemptedFragment$$Lambda$3(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment) {
        this.arg$1 = cityRegistrationReviewExemptedFragment;
    }

    public static Listener lambdaFactory$(CityRegistrationReviewExemptedFragment cityRegistrationReviewExemptedFragment) {
        return new CityRegistrationReviewExemptedFragment$$Lambda$3(cityRegistrationReviewExemptedFragment);
    }

    public void inputIsValid(boolean z) {
        this.arg$1.enableSaveButton(z);
    }
}
