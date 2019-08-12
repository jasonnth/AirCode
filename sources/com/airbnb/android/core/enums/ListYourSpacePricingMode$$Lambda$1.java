package com.airbnb.android.core.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class ListYourSpacePricingMode$$Lambda$1 implements Predicate {
    private final int arg$1;

    private ListYourSpacePricingMode$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new ListYourSpacePricingMode$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return ListYourSpacePricingMode.lambda$fromServerKey$0(this.arg$1, (ListYourSpacePricingMode) obj);
    }
}
