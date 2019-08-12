package com.airbnb.android.lib.payments.addpayment.fragments;

import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;

final /* synthetic */ class AddPaymentMethodFragment$$Lambda$1 implements PaymentMethodNonceCreatedListener {
    private final AddPaymentMethodFragment arg$1;

    private AddPaymentMethodFragment$$Lambda$1(AddPaymentMethodFragment addPaymentMethodFragment) {
        this.arg$1 = addPaymentMethodFragment;
    }

    public static PaymentMethodNonceCreatedListener lambdaFactory$(AddPaymentMethodFragment addPaymentMethodFragment) {
        return new AddPaymentMethodFragment$$Lambda$1(addPaymentMethodFragment);
    }

    public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentMethodNonce) {
        AddPaymentMethodFragment.lambda$new$1(this.arg$1, paymentMethodNonce);
    }
}
