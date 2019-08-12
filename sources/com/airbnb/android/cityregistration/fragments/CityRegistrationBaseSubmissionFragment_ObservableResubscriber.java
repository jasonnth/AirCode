package com.airbnb.android.cityregistration.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CityRegistrationBaseSubmissionFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CityRegistrationBaseSubmissionFragment_ObservableResubscriber(CityRegistrationBaseSubmissionFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRegistrationProcessesRequestListener, "CityRegistrationBaseSubmissionFragment_listingRegistrationProcessesRequestListener");
        group.resubscribeAll(target.listingRegistrationProcessesRequestListener);
        setTag((AutoTaggableObserver) target.createListingRegistrationRequestListener, "CityRegistrationBaseSubmissionFragment_createListingRegistrationRequestListener");
        group.resubscribeAll(target.createListingRegistrationRequestListener);
    }
}
