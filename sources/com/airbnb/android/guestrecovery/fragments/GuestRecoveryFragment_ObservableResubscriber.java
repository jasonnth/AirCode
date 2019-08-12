package com.airbnb.android.guestrecovery.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class GuestRecoveryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public GuestRecoveryFragment_ObservableResubscriber(GuestRecoveryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "GuestRecoveryFragment_reservationListener");
        group.resubscribeAll(target.reservationListener);
        setTag((AutoTaggableObserver) target.listingReplacementListener, "GuestRecoveryFragment_listingReplacementListener");
        group.resubscribeAll(target.listingReplacementListener);
    }
}
