package com.airbnb.android.core.paidamenities.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class PaidAmenityServerType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private PaidAmenityServerType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new PaidAmenityServerType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((PaidAmenityServerType) obj).serverKey.equals(this.arg$1);
    }
}
