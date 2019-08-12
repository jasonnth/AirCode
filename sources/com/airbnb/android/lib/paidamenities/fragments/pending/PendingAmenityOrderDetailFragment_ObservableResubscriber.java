package com.airbnb.android.lib.paidamenities.fragments.pending;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PendingAmenityOrderDetailFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PendingAmenityOrderDetailFragment_ObservableResubscriber(PendingAmenityOrderDetailFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateStatusListener, "PendingAmenityOrderDetailFragment_updateStatusListener");
        group.resubscribeAll(target.updateStatusListener);
    }
}
