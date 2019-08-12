package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostReservationObjectFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostReservationObjectFragment_ObservableResubscriber(HostReservationObjectFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationRequestListener, "HostReservationObjectFragment_reservationRequestListener");
        group.resubscribeAll(target.reservationRequestListener);
        setTag((AutoTaggableObserver) target.inquiryListener, "HostReservationObjectFragment_inquiryListener");
        group.resubscribeAll(target.inquiryListener);
    }
}
