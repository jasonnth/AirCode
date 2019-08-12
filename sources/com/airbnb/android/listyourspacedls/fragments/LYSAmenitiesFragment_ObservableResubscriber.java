package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSAmenitiesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSAmenitiesFragment_ObservableResubscriber(LYSAmenitiesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSAmenitiesFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
