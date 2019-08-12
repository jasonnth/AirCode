package com.airbnb.android.lib.paidamenities.fragments.pending;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PendingAmenityOrderListFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PendingAmenityOrderListFragment_ObservableResubscriber(PendingAmenityOrderListFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchPaidAmenityOrdersListener, "PendingAmenityOrderListFragment_fetchPaidAmenityOrdersListener");
        group.resubscribeAll(target.fetchPaidAmenityOrdersListener);
    }
}
