package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CityRegistrationApplicationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CityRegistrationApplicationFragment_ObservableResubscriber(CityRegistrationApplicationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRegistrationProcessesRequestListener, "CityRegistrationApplicationFragment_listingRegistrationProcessesRequestListener");
        group.resubscribeAll(target.listingRegistrationProcessesRequestListener);
        setTag((AutoTaggableObserver) target.createListingRegistrationRequestListener, "CityRegistrationApplicationFragment_createListingRegistrationRequestListener");
        group.resubscribeAll(target.createListingRegistrationRequestListener);
    }
}
