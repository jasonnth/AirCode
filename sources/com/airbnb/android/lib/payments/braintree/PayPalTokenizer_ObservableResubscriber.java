package com.airbnb.android.lib.payments.braintree;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PayPalTokenizer_ObservableResubscriber extends BaseObservableResubscriber {
    public PayPalTokenizer_ObservableResubscriber(PayPalTokenizer target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.vaultPayPalListener, "PayPalTokenizer_vaultPayPalListener");
        group.resubscribeAll(target.vaultPayPalListener);
    }
}
