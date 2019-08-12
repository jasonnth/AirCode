package com.airbnb.android.booking.fragments;

import com.braintreepayments.api.interfaces.BraintreeResponseListener;

final /* synthetic */ class PaymentManagerFragment$$Lambda$8 implements BraintreeResponseListener {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$8(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static BraintreeResponseListener lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$8(paymentManagerFragment);
    }

    public void onResponse(Object obj) {
        PaymentManagerFragment.lambda$null$3(this.arg$1, (Boolean) obj);
    }
}
