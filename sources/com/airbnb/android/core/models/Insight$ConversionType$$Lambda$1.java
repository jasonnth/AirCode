package com.airbnb.android.core.models;

import com.airbnb.android.core.models.Insight.ConversionType;
import com.google.common.base.Predicate;

final /* synthetic */ class Insight$ConversionType$$Lambda$1 implements Predicate {
    private final int arg$1;

    private Insight$ConversionType$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new Insight$ConversionType$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return ConversionType.lambda$fromServerKey$0(this.arg$1, (ConversionType) obj);
    }
}
