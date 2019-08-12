package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.responses.BraintreeClientTokenResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PaymentManagerFragment$$Lambda$1 implements Action1 {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$1(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static Action1 lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$1(paymentManagerFragment);
    }

    public void call(Object obj) {
        PaymentManagerFragment.lambda$new$1(this.arg$1, (BraintreeClientTokenResponse) obj);
    }
}
