package com.airbnb.android.nestedlistings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class NestedListingsActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public NestedListingsActivity_ObservableResubscriber(NestedListingsActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.nestedListingListener, "NestedListingsActivity_nestedListingListener");
        group.resubscribeAll(target.nestedListingListener);
    }
}
