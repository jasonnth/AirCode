package com.airbnb.android.lib.cancellation;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DLSCancelReservationReasonFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DLSCancelReservationReasonFragment_ObservableResubscriber(DLSCancelReservationReasonFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "DLSCancelReservationReasonFragment_reservationListener");
        group.resubscribeAll(target.reservationListener);
    }
}
