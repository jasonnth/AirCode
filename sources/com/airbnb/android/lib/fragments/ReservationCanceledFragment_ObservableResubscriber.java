package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReservationCanceledFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ReservationCanceledFragment_ObservableResubscriber(ReservationCanceledFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.deleteReservationRequestListener, "ReservationCanceledFragment_deleteReservationRequestListener");
        group.resubscribeAll(target.deleteReservationRequestListener);
    }
}
