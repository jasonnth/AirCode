package com.airbnb.android.core.models;

import com.google.common.base.Predicate;
import java.util.EnumSet;

final /* synthetic */ class ReservationSummary$$Lambda$1 implements Predicate {
    private final EnumSet arg$1;

    private ReservationSummary$$Lambda$1(EnumSet enumSet) {
        this.arg$1 = enumSet;
    }

    public static Predicate lambdaFactory$(EnumSet enumSet) {
        return new ReservationSummary$$Lambda$1(enumSet);
    }

    public boolean apply(Object obj) {
        return this.arg$1.contains(((PaidAmenityOrder) obj).getStatus());
    }
}
