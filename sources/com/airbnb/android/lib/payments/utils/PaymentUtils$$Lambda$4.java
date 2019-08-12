package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentUtils$$Lambda$4 implements Predicate {
    private static final PaymentUtils$$Lambda$4 instance = new PaymentUtils$$Lambda$4();

    private PaymentUtils$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((PaymentOption) obj).isBusinessTravelPaymentOption();
    }
}
