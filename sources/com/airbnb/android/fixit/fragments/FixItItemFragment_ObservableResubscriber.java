package com.airbnb.android.fixit.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class FixItItemFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public FixItItemFragment_ObservableResubscriber(FixItItemFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.itemUpdateReponseListener, "FixItItemFragment_itemUpdateReponseListener");
        group.resubscribeAll(target.itemUpdateReponseListener);
    }
}
