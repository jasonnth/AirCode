package com.airbnb.android.core.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class LegacyBedType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private LegacyBedType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new LegacyBedType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((LegacyBedType) obj).serverDescKey.equalsIgnoreCase(this.arg$1);
    }
}
