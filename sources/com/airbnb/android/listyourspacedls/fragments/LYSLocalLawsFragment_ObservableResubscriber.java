package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSLocalLawsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSLocalLawsFragment_ObservableResubscriber(LYSLocalLawsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSLocalLawsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
