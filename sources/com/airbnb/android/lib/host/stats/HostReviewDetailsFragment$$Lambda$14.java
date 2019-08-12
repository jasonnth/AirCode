package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Review;
import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$14 implements Predicate {
    private static final HostReviewDetailsFragment$$Lambda$14 instance = new HostReviewDetailsFragment$$Lambda$14();

    private HostReviewDetailsFragment$$Lambda$14() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return HostReviewDetailsFragment.lambda$filterForHomeReviews$15((Review) obj);
    }
}
