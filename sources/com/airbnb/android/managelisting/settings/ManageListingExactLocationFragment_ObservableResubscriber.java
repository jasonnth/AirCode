package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingExactLocationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingExactLocationFragment_ObservableResubscriber(ManageListingExactLocationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingExactLocationFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
