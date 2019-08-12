package com.airbnb.android.lib.payments.creditcard.brazil.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BrazilCreditCardDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BrazilCreditCardDetailsFragment_ObservableResubscriber(BrazilCreditCardDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "BrazilCreditCardDetailsFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
