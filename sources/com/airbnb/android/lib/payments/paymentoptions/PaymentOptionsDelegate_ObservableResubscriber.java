package com.airbnb.android.lib.payments.paymentoptions;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PaymentOptionsDelegate_ObservableResubscriber extends BaseObservableResubscriber {
    public PaymentOptionsDelegate_ObservableResubscriber(PaymentOptionsDelegate target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.paymentOptionsListener, "PaymentOptionsDelegate_paymentOptionsListener");
        group.resubscribeAll(target.paymentOptionsListener);
    }
}
