package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingReorderCheckInStepsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingReorderCheckInStepsFragment_ObservableResubscriber(ManageListingReorderCheckInStepsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reorderStepsListener, "ManageListingReorderCheckInStepsFragment_reorderStepsListener");
        group.resubscribeAll(target.reorderStepsListener);
    }
}
