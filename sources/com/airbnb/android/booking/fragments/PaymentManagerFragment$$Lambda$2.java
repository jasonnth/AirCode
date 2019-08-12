package com.airbnb.android.booking.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class PaymentManagerFragment$$Lambda$2 implements Action1 {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$2(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static Action1 lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$2(paymentManagerFragment);
    }

    public void call(Object obj) {
        this.arg$1.paymentManagerListener.onPaymentManagerError(300, new IllegalStateException("Unable to acquire braintree token"));
    }
}
