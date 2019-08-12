package com.airbnb.android.lib.payments.fragments;

import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.google.android.gms.common.api.GoogleApiClient;

final /* synthetic */ class PaymentOptionsFragment$$Lambda$3 implements BraintreeResponseListener {
    private final PaymentOptionsFragment arg$1;

    private PaymentOptionsFragment$$Lambda$3(PaymentOptionsFragment paymentOptionsFragment) {
        this.arg$1 = paymentOptionsFragment;
    }

    public static BraintreeResponseListener lambdaFactory$(PaymentOptionsFragment paymentOptionsFragment) {
        return new PaymentOptionsFragment$$Lambda$3(paymentOptionsFragment);
    }

    public void onResponse(Object obj) {
        this.arg$1.googleApiClient = (GoogleApiClient) obj;
    }
}
