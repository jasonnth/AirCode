package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.google.common.base.Predicate;

final /* synthetic */ class PaymentInstrumentsFragment$$Lambda$2 implements Predicate {
    private static final PaymentInstrumentsFragment$$Lambda$2 instance = new PaymentInstrumentsFragment$$Lambda$2();

    private PaymentInstrumentsFragment$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PaymentInstrumentsFragment.lambda$onCreateView$0((OldPaymentInstrument) obj);
    }
}
