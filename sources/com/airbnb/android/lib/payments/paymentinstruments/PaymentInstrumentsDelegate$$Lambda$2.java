package com.airbnb.android.lib.payments.paymentinstruments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PaymentInstrumentsDelegate$$Lambda$2 implements Action1 {
    private final PaymentInstrumentsDelegate arg$1;

    private PaymentInstrumentsDelegate$$Lambda$2(PaymentInstrumentsDelegate paymentInstrumentsDelegate) {
        this.arg$1 = paymentInstrumentsDelegate;
    }

    public static Action1 lambdaFactory$(PaymentInstrumentsDelegate paymentInstrumentsDelegate) {
        return new PaymentInstrumentsDelegate$$Lambda$2(paymentInstrumentsDelegate);
    }

    public void call(Object obj) {
        this.arg$1.getDelegateListener().onPaymentInstrumentFailure((AirRequestNetworkException) obj);
    }
}
