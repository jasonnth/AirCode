package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class PaymentOption$$Lambda$2 implements Function {
    private static final PaymentOption$$Lambda$2 instance = new PaymentOption$$Lambda$2();

    private PaymentOption$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((PaymentOption) obj).toLegacyPaymentInstrument();
    }
}
