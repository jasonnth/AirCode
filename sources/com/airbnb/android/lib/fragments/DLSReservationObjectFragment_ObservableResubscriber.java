package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DLSReservationObjectFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DLSReservationObjectFragment_ObservableResubscriber(DLSReservationObjectFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "DLSReservationObjectFragment_reservationListener");
        group.resubscribeAll(target.reservationListener);
        setTag((AutoTaggableObserver) target.inquiryListener, "DLSReservationObjectFragment_inquiryListener");
        group.resubscribeAll(target.inquiryListener);
        setTag((AutoTaggableObserver) target.helpThreadRequestListener, "DLSReservationObjectFragment_helpThreadRequestListener");
        group.resubscribeAll(target.helpThreadRequestListener);
        setTag((AutoTaggableObserver) target.localAttractionsRequestListener, "DLSReservationObjectFragment_localAttractionsRequestListener");
        group.resubscribeAll(target.localAttractionsRequestListener);
    }
}
