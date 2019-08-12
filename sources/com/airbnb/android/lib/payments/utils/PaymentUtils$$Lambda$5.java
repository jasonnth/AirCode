package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentUtils$$Lambda$5 implements Predicate {
    private final PaymentMethodType arg$1;

    private PaymentUtils$$Lambda$5(PaymentMethodType paymentMethodType) {
        this.arg$1 = paymentMethodType;
    }

    public static Predicate lambdaFactory$(PaymentMethodType paymentMethodType) {
        return new PaymentUtils$$Lambda$5(paymentMethodType);
    }

    public boolean apply(Object obj) {
        return ((PaymentOption) obj).getPaymentMethodType().equals(this.arg$1.getServerKey());
    }
}
