package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentUtils$$Lambda$3 implements Predicate {
    private static final PaymentUtils$$Lambda$3 instance = new PaymentUtils$$Lambda$3();

    private PaymentUtils$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PaymentUtils.lambda$getExistingPaymentOptions$2((PaymentOption) obj);
    }
}
