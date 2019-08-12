package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingRoomsGuestsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingRoomsGuestsFragment_ObservableResubscriber(ManageListingRoomsGuestsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingRoomsGuestsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
