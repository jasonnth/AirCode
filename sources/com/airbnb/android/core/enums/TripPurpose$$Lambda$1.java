package com.airbnb.android.core.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class TripPurpose$$Lambda$1 implements Predicate {
    private final String arg$1;

    private TripPurpose$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new TripPurpose$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((TripPurpose) obj).serverKey.equalsIgnoreCase(this.arg$1);
    }
}
