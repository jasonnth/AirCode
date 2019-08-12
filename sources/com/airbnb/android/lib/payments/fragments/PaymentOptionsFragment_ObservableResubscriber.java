package com.airbnb.android.lib.payments.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PaymentOptionsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PaymentOptionsFragment_ObservableResubscriber(PaymentOptionsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createAndroidPayInstrumentListener, "PaymentOptionsFragment_createAndroidPayInstrumentListener");
        group.resubscribeAll(target.createAndroidPayInstrumentListener);
    }
}
