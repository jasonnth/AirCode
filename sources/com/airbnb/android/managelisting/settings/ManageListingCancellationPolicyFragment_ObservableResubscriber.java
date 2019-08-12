package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingCancellationPolicyFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingCancellationPolicyFragment_ObservableResubscriber(ManageListingCancellationPolicyFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingCancellationPolicyFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
