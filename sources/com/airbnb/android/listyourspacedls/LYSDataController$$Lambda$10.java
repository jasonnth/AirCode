package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.models.ListingRoom;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSDataController$$Lambda$10 implements Predicate {
    private final int arg$1;

    private LYSDataController$$Lambda$10(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new LYSDataController$$Lambda$10(i);
    }

    public boolean apply(Object obj) {
        return LYSDataController.lambda$getRoomByNumber$2(this.arg$1, (ListingRoom) obj);
    }
}
