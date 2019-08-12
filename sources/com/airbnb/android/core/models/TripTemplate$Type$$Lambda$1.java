package com.airbnb.android.core.models;

import com.airbnb.android.core.models.TripTemplate.Type;
import com.google.common.base.Predicate;

final /* synthetic */ class TripTemplate$Type$$Lambda$1 implements Predicate {
    private final int arg$1;

    private TripTemplate$Type$$Lambda$1(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new TripTemplate$Type$$Lambda$1(i);
    }

    public boolean apply(Object obj) {
        return Type.lambda$fromId$0(this.arg$1, (Type) obj);
    }
}
