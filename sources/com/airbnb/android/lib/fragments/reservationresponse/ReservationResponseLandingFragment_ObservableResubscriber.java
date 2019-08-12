package com.airbnb.android.lib.fragments.reservationresponse;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReservationResponseLandingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ReservationResponseLandingFragment_ObservableResubscriber(ReservationResponseLandingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateRequestListener, "ReservationResponseLandingFragment_updateRequestListener");
        group.resubscribeAll(target.updateRequestListener);
    }
}
