package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSDiscountsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSDiscountsFragment_ObservableResubscriber(LYSDiscountsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSDiscountsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
