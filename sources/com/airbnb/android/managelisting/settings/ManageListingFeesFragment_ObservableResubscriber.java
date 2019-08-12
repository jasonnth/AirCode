package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingFeesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingFeesFragment_ObservableResubscriber(ManageListingFeesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingFeesFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
