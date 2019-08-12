package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingUnlistingReasonSheetFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingUnlistingReasonSheetFragment_ObservableResubscriber(ManageListingUnlistingReasonSheetFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingUnlistingReasonSheetFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
