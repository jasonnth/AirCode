package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingAllCheckinMethodsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingAllCheckinMethodsFragment_ObservableResubscriber(ManageListingAllCheckinMethodsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.refreshMethodsListener, "ManageListingAllCheckinMethodsFragment_refreshMethodsListener");
        group.resubscribeAll(target.refreshMethodsListener);
    }
}
