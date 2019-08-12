package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateAmenityLandingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateAmenityLandingFragment_ObservableResubscriber(CreateAmenityLandingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingsRequestListener, "CreateAmenityLandingFragment_listingsRequestListener");
        group.resubscribeAll(target.listingsRequestListener);
    }
}
