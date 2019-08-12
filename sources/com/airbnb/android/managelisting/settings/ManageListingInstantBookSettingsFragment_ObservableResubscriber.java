package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingInstantBookSettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingInstantBookSettingsFragment_ObservableResubscriber(ManageListingInstantBookSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingInstantBookSettingsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
