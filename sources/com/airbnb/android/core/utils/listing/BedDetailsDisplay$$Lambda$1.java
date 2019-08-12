package com.airbnb.android.core.utils.listing;

import com.airbnb.android.core.models.BedType;
import com.google.common.base.Predicate;

final /* synthetic */ class BedDetailsDisplay$$Lambda$1 implements Predicate {
    private final boolean arg$1;

    private BedDetailsDisplay$$Lambda$1(boolean z) {
        this.arg$1 = z;
    }

    public static Predicate lambdaFactory$(boolean z) {
        return new BedDetailsDisplay$$Lambda$1(z);
    }

    public boolean apply(Object obj) {
        return BedDetailsDisplay.lambda$getRoomDescription$0(this.arg$1, (BedType) obj);
    }
}
