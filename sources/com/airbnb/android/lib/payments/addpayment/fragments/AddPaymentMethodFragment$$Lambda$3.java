package com.airbnb.android.lib.payments.addpayment.fragments;

import com.braintreepayments.api.interfaces.BraintreeErrorListener;

final /* synthetic */ class AddPaymentMethodFragment$$Lambda$3 implements BraintreeErrorListener {
    private final AddPaymentMethodFragment arg$1;

    private AddPaymentMethodFragment$$Lambda$3(AddPaymentMethodFragment addPaymentMethodFragment) {
        this.arg$1 = addPaymentMethodFragment;
    }

    public static BraintreeErrorListener lambdaFactory$(AddPaymentMethodFragment addPaymentMethodFragment) {
        return new AddPaymentMethodFragment$$Lambda$3(addPaymentMethodFragment);
    }

    public void onError(Exception exc) {
        AddPaymentMethodFragment.lambda$new$3(this.arg$1, exc);
    }
}
