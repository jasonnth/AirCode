package com.airbnb.android.core.payments.models;

import com.google.common.base.Predicate;

final /* synthetic */ class PaymentMethodType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private PaymentMethodType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new PaymentMethodType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((PaymentMethodType) obj).serverKey.equals(this.arg$1);
    }
}
