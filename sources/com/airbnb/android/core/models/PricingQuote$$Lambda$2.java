package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class PricingQuote$$Lambda$2 implements Predicate {
    private static final PricingQuote$$Lambda$2 instance = new PricingQuote$$Lambda$2();

    private PricingQuote$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PricingQuote.lambda$getCouponCurrencyAmount$1((Price) obj);
    }
}
