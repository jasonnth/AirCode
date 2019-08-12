package com.airbnb.android.booking.fragments;

import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;

final /* synthetic */ class PaymentManagerFragment$$Lambda$5 implements PaymentMethodNonceCreatedListener {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$5(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static PaymentMethodNonceCreatedListener lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$5(paymentManagerFragment);
    }

    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        PaymentManagerFragment.lambda$new$7(this.arg$1, paymentMethodNonce);
    }
}
