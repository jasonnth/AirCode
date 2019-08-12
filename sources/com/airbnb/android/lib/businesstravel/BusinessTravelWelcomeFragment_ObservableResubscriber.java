package com.airbnb.android.lib.businesstravel;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BusinessTravelWelcomeFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BusinessTravelWelcomeFragment_ObservableResubscriber(BusinessTravelWelcomeFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.welcomeDataRequestListener, "BusinessTravelWelcomeFragment_welcomeDataRequestListener");
        group.resubscribeAll(target.welcomeDataRequestListener);
    }
}
