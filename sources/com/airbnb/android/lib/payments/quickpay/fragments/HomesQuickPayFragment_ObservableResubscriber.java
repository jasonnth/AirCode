package com.airbnb.android.lib.payments.quickpay.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HomesQuickPayFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HomesQuickPayFragment_ObservableResubscriber(HomesQuickPayFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "HomesQuickPayFragment_reservationListener");
        group.resubscribeAll(target.reservationListener);
    }
}
