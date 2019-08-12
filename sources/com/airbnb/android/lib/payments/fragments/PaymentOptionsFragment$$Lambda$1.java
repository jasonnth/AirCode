package com.airbnb.android.lib.payments.fragments;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PaymentOptionsFragment$$Lambda$1 implements Action1 {
    private final PaymentOptionsFragment arg$1;

    private PaymentOptionsFragment$$Lambda$1(PaymentOptionsFragment paymentOptionsFragment) {
        this.arg$1 = paymentOptionsFragment;
    }

    public static Action1 lambdaFactory$(PaymentOptionsFragment paymentOptionsFragment) {
        return new PaymentOptionsFragment$$Lambda$1(paymentOptionsFragment);
    }

    public void call(Object obj) {
        PaymentOptionsFragment.lambda$new$1(this.arg$1, (PaymentInstrumentResponse) obj);
    }
}
