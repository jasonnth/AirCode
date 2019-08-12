package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.listing.adapters.CityRegistrationExemptionAdapter.Listener;

final /* synthetic */ class LYSCityRegistrationReviewExemptedFragment$$Lambda$4 implements Listener {
    private final LYSCityRegistrationReviewExemptedFragment arg$1;

    private LYSCityRegistrationReviewExemptedFragment$$Lambda$4(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        this.arg$1 = lYSCityRegistrationReviewExemptedFragment;
    }

    public static Listener lambdaFactory$(LYSCityRegistrationReviewExemptedFragment lYSCityRegistrationReviewExemptedFragment) {
        return new LYSCityRegistrationReviewExemptedFragment$$Lambda$4(lYSCityRegistrationReviewExemptedFragment);
    }

    public void inputIsValid(boolean z) {
        this.arg$1.enableSaveButton(z);
    }
}
