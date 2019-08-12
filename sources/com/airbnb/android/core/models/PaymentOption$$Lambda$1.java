package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class PaymentOption$$Lambda$1 implements Predicate {
    private static final PaymentOption$$Lambda$1 instance = new PaymentOption$$Lambda$1();

    private PaymentOption$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PaymentOption.lambda$toValidPaymentInstruments$0((PaymentOption) obj);
    }
}
