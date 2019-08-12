package com.airbnb.android.lib.payments.paymentoptions;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PaymentOptionsDelegate$$Lambda$2 implements Action1 {
    private final PaymentOptionsDelegate arg$1;

    private PaymentOptionsDelegate$$Lambda$2(PaymentOptionsDelegate paymentOptionsDelegate) {
        this.arg$1 = paymentOptionsDelegate;
    }

    public static Action1 lambdaFactory$(PaymentOptionsDelegate paymentOptionsDelegate) {
        return new PaymentOptionsDelegate$$Lambda$2(paymentOptionsDelegate);
    }

    public void call(Object obj) {
        this.arg$1.getPaymentOptionsDelegateListener().onPaymentOptionsRequestError((AirRequestNetworkException) obj);
    }
}
