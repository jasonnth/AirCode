package com.airbnb.android.booking.fragments;

import com.braintreepayments.api.interfaces.BraintreeErrorListener;

final /* synthetic */ class PaymentManagerFragment$$Lambda$3 implements BraintreeErrorListener {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$3(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static BraintreeErrorListener lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$3(paymentManagerFragment);
    }

    public void onError(Exception exc) {
        this.arg$1.paymentManagerListener.onPaymentManagerError(302, exc);
    }
}
