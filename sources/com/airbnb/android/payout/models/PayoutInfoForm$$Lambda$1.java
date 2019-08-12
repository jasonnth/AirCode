package com.airbnb.android.payout.models;

import com.google.common.base.Predicate;

final /* synthetic */ class PayoutInfoForm$$Lambda$1 implements Predicate {
    private static final PayoutInfoForm$$Lambda$1 instance = new PayoutInfoForm$$Lambda$1();

    private PayoutInfoForm$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PayoutInfoForm.lambda$shouldShowAccountType$0((ExtraPayoutAttribute) obj);
    }
}
