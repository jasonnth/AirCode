package com.airbnb.android.core.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DLSCancelReservationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DLSCancelReservationFragment_ObservableResubscriber(DLSCancelReservationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.cancelListener, "DLSCancelReservationFragment_cancelListener");
        group.resubscribeAll(target.cancelListener);
    }
}
