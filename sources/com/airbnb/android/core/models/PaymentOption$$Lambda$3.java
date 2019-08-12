package com.airbnb.android.core.models;

import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentOption$$Lambda$3 implements Predicate {
    private static final PaymentOption$$Lambda$3 instance = new PaymentOption$$Lambda$3();

    private PaymentOption$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PaymentOption.lambda$toValidPaymentInstruments$1((OldPaymentInstrument) obj);
    }
}
