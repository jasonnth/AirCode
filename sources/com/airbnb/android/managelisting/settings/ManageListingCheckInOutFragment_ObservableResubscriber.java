package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingCheckInOutFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingCheckInOutFragment_ObservableResubscriber(ManageListingCheckInOutFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingUpdateListener, "ManageListingCheckInOutFragment_listingUpdateListener");
        group.resubscribeAll(target.listingUpdateListener);
    }
}
