package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PaymentManagerFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PaymentManagerFragment_ObservableResubscriber(PaymentManagerFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.braintreeTokenRequestListener, "PaymentManagerFragment_braintreeTokenRequestListener");
        group.resubscribeAll(target.braintreeTokenRequestListener);
    }
}
