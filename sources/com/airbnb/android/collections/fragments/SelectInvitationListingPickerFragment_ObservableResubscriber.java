package com.airbnb.android.collections.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SelectInvitationListingPickerFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public SelectInvitationListingPickerFragment_ObservableResubscriber(SelectInvitationListingPickerFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingsListener, "SelectInvitationListingPickerFragment_listingsListener");
        group.resubscribeAll(target.listingsListener);
    }
}
