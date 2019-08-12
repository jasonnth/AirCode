package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSGuestBookFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSGuestBookFragment_ObservableResubscriber(LYSGuestBookFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSGuestBookFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
