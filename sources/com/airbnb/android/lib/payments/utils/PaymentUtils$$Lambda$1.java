package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentUtils$$Lambda$1 implements Predicate {
    private static final PaymentUtils$$Lambda$1 instance = new PaymentUtils$$Lambda$1();

    private PaymentUtils$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PaymentUtils.isValidPaymentOption((PaymentOption) obj);
    }
}
