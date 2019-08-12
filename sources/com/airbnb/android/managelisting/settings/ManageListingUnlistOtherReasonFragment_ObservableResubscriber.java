package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingUnlistOtherReasonFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingUnlistOtherReasonFragment_ObservableResubscriber(ManageListingUnlistOtherReasonFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingUnlistOtherReasonFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
