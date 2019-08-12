package com.airbnb.android.core.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class BedDetailType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private BedDetailType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new BedDetailType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((BedDetailType) obj).serverDescKey.equalsIgnoreCase(this.arg$1);
    }
}
