package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class Price$$Lambda$1 implements Predicate {
    private static final Price$$Lambda$1 instance = new Price$$Lambda$1();

    private Price$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return Price.lambda$getGiftCredit$0((Price) obj);
    }
}
