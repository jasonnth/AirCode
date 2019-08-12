package com.airbnb.android.lib.payments.braintree;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PayPalTokenizer$$Lambda$1 implements Action1 {
    private final PayPalTokenizer arg$1;

    private PayPalTokenizer$$Lambda$1(PayPalTokenizer payPalTokenizer) {
        this.arg$1 = payPalTokenizer;
    }

    public static Action1 lambdaFactory$(PayPalTokenizer payPalTokenizer) {
        return new PayPalTokenizer$$Lambda$1(payPalTokenizer);
    }

    public void call(Object obj) {
        this.arg$1.getPayPalListener().onPayPalVaulted(((PaymentInstrumentResponse) obj).paymentInstrument);
    }
}
