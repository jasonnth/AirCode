package com.airbnb.android.cityregistration.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CityRegistrationReviewFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CityRegistrationReviewFragment_ObservableResubscriber(CityRegistrationReviewFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingLicenseRequestListener, "CityRegistrationReviewFragment_updateListingLicenseRequestListener");
        group.resubscribeAll(target.updateListingLicenseRequestListener);
    }
}
