package com.airbnb.android.lib.payments.braintree;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PayPalTokenizer$$Lambda$2 implements Action1 {
    private final PayPalTokenizer arg$1;

    private PayPalTokenizer$$Lambda$2(PayPalTokenizer payPalTokenizer) {
        this.arg$1 = payPalTokenizer;
    }

    public static Action1 lambdaFactory$(PayPalTokenizer payPalTokenizer) {
        return new PayPalTokenizer$$Lambda$2(payPalTokenizer);
    }

    public void call(Object obj) {
        this.arg$1.getPayPalListener().onPayPalVaultError((AirRequestNetworkException) obj);
    }
}
