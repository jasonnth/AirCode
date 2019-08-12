package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReservationResponseActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public ReservationResponseActivity_ObservableResubscriber(ReservationResponseActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.declineReservationRequestListener, "ReservationResponseActivity_declineReservationRequestListener");
        group.resubscribeAll(target.declineReservationRequestListener);
        setTag((AutoTaggableObserver) target.reservationRequestListener, "ReservationResponseActivity_reservationRequestListener");
        group.resubscribeAll(target.reservationRequestListener);
    }
}
