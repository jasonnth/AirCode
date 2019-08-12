package com.airbnb.android.core.models.payments;

import com.google.common.base.Predicate;

final /* synthetic */ class PaymentInstrumentType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private PaymentInstrumentType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new PaymentInstrumentType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((C6200PaymentInstrumentType) obj).serverKey.equals(this.arg$1);
    }
}
