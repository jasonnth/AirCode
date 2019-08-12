package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.cityregistration.adapters.CityRegistrationSubmissionAdapter.Listener;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;

final /* synthetic */ class CityRegistrationApplicationFragment$$Lambda$1 implements Listener {
    private final CityRegistrationApplicationFragment arg$1;

    private CityRegistrationApplicationFragment$$Lambda$1(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        this.arg$1 = cityRegistrationApplicationFragment;
    }

    public static Listener lambdaFactory$(CityRegistrationApplicationFragment cityRegistrationApplicationFragment) {
        return new CityRegistrationApplicationFragment$$Lambda$1(cityRegistrationApplicationFragment);
    }

    public void editAnswersForInputGroup(ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup) {
        this.arg$1.controller.showInputGroupModal(listingRegistrationProcessInputGroup);
    }
}
