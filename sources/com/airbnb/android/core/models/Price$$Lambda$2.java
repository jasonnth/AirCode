package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class Price$$Lambda$2 implements Predicate {
    private static final Price$$Lambda$2 instance = new Price$$Lambda$2();

    private Price$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return Price.lambda$getCouponCode$1((Price) obj);
    }
}
