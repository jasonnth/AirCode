package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingAmenitiesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingAmenitiesFragment_ObservableResubscriber(ManageListingAmenitiesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingAmenitiesFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
