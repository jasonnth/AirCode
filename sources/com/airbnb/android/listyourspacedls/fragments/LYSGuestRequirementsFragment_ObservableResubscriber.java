package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSGuestRequirementsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSGuestRequirementsFragment_ObservableResubscriber(LYSGuestRequirementsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSGuestRequirementsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
