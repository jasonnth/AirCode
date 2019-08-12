package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PayPalPayoutFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PayPalPayoutFragment_ObservableResubscriber(PayPalPayoutFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createPayoutRequestListener, "PayPalPayoutFragment_createPayoutRequestListener");
        group.resubscribeAll(target.createPayoutRequestListener);
    }
}
