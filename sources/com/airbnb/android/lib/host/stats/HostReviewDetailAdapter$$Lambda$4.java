package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailAdapter$$Lambda$4 implements Predicate {
    private static final HostReviewDetailAdapter$$Lambda$4 instance = new HostReviewDetailAdapter$$Lambda$4();

    private HostReviewDetailAdapter$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HostReviewDetailAdapter.lambda$setRecentReviewCards$2((Listing) obj);
    }
}
