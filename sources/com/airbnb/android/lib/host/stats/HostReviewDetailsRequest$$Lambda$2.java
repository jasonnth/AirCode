package com.airbnb.android.lib.host.stats;

import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailsRequest$$Lambda$2 implements Predicate {
    private static final HostReviewDetailsRequest$$Lambda$2 instance = new HostReviewDetailsRequest$$Lambda$2();

    private HostReviewDetailsRequest$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HostReviewDetailsRequest.lambda$new$1((Long) obj);
    }
}
