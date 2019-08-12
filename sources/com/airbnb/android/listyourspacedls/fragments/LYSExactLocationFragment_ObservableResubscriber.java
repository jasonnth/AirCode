package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSExactLocationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSExactLocationFragment_ObservableResubscriber(LYSExactLocationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSExactLocationFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
