package com.airbnb.android.cityregistration.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CityRegistrationActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public CityRegistrationActivity_ObservableResubscriber(CityRegistrationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingRegistrationOpenSubmissionListener, "CityRegistrationActivity_updateListingRegistrationOpenSubmissionListener");
        group.resubscribeAll(target.updateListingRegistrationOpenSubmissionListener);
    }
}
