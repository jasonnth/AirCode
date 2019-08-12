package com.airbnb.android.cohosting.utils;

import com.airbnb.android.core.models.ListingManager;
import com.google.common.base.Predicate;

final /* synthetic */ class CohostingUtil$$Lambda$2 implements Predicate {
    private final long arg$1;

    private CohostingUtil$$Lambda$2(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new CohostingUtil$$Lambda$2(j);
    }

    public boolean apply(Object obj) {
        return CohostingUtil.lambda$isCurrentUserListingAdmin$1(this.arg$1, (ListingManager) obj);
    }
}
