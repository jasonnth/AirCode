package com.airbnb.android.nestedlistings.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class NestedListingsChooseChildrenFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public NestedListingsChooseChildrenFragment_ObservableResubscriber(NestedListingsChooseChildrenFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateNestedListingListener, "NestedListingsChooseChildrenFragment_updateNestedListingListener");
        group.resubscribeAll(target.updateNestedListingListener);
        setTag((AutoTaggableObserver) target.nestedListingRefreshListener, "NestedListingsChooseChildrenFragment_nestedListingRefreshListener");
        group.resubscribeAll(target.nestedListingRefreshListener);
    }
}
