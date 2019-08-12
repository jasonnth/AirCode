package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSGuestAdditionalRequirementsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSGuestAdditionalRequirementsFragment_ObservableResubscriber(LYSGuestAdditionalRequirementsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSGuestAdditionalRequirementsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
