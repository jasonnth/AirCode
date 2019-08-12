package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.listing.adapters.ListingRegistrationAdapter.Listener;

final /* synthetic */ class CityRegistrationReviewFragment$$Lambda$3 implements Listener {
    private final CityRegistrationReviewFragment arg$1;

    private CityRegistrationReviewFragment$$Lambda$3(CityRegistrationReviewFragment cityRegistrationReviewFragment) {
        this.arg$1 = cityRegistrationReviewFragment;
    }

    public static Listener lambdaFactory$(CityRegistrationReviewFragment cityRegistrationReviewFragment) {
        return new CityRegistrationReviewFragment$$Lambda$3(cityRegistrationReviewFragment);
    }

    public void inputHasChanged(boolean z) {
        this.arg$1.setSaveButtonVisible(z);
    }
}
