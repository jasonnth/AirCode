package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSCityRegistrationApplicationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSCityRegistrationApplicationFragment_ObservableResubscriber(LYSCityRegistrationApplicationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRegistrationProcessesRequestListener, "LYSCityRegistrationApplicationFragment_listingRegistrationProcessesRequestListener");
        group.resubscribeAll(target.listingRegistrationProcessesRequestListener);
    }
}
