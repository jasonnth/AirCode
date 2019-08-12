package com.airbnb.android.lib.adapters;

import com.airbnb.android.core.models.SupportPhoneNumber;
import com.google.common.base.Predicate;

final /* synthetic */ class SupportNumbersAdapter$$Lambda$1 implements Predicate {
    private static final SupportNumbersAdapter$$Lambda$1 instance = new SupportNumbersAdapter$$Lambda$1();

    private SupportNumbersAdapter$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return SupportNumbersAdapter.lambda$showSupportNumbers$0((SupportPhoneNumber) obj);
    }
}
