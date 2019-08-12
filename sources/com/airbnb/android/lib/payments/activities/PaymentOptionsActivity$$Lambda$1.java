package com.airbnb.android.lib.payments.activities;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentOptionsActivity$$Lambda$1 implements Predicate {
    private static final PaymentOptionsActivity$$Lambda$1 instance = new PaymentOptionsActivity$$Lambda$1();

    private PaymentOptionsActivity$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((PaymentOption) obj).getPaymentMethodType().equals(PaymentMethodType.CreditCard.getServerKey());
    }
}
