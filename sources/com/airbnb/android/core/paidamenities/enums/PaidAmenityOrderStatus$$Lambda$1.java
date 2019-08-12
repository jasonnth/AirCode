package com.airbnb.android.core.paidamenities.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class PaidAmenityOrderStatus$$Lambda$1 implements Predicate {
    private final String arg$1;

    private PaidAmenityOrderStatus$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new PaidAmenityOrderStatus$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((PaidAmenityOrderStatus) obj).serverKey.equals(this.arg$1);
    }
}
