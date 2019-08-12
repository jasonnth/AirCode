package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSCityRegistrationReviewFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSCityRegistrationReviewFragment_ObservableResubscriber(LYSCityRegistrationReviewFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingLicenseRequestListener, "LYSCityRegistrationReviewFragment_updateListingLicenseRequestListener");
        group.resubscribeAll(target.updateListingLicenseRequestListener);
    }
}
