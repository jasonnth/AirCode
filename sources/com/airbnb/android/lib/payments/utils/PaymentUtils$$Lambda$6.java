package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentUtils$$Lambda$6 implements Predicate {
    private static final PaymentUtils$$Lambda$6 instance = new PaymentUtils$$Lambda$6();

    private PaymentUtils$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PaymentUtils.lambda$getUniqueSkeletonPaymentMethods$5((PaymentOption) obj);
    }
}
