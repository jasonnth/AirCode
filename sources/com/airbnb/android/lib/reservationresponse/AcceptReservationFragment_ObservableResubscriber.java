package com.airbnb.android.lib.reservationresponse;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AcceptReservationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AcceptReservationFragment_ObservableResubscriber(AcceptReservationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateRequestListener, "AcceptReservationFragment_updateRequestListener");
        group.resubscribeAll(target.updateRequestListener);
    }
}
