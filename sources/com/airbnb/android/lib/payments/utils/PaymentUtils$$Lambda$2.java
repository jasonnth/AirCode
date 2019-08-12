package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentUtils$$Lambda$2 implements Predicate {
    private static final PaymentUtils$$Lambda$2 instance = new PaymentUtils$$Lambda$2();

    private PaymentUtils$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((PaymentOption) obj).isDefault();
    }
}
