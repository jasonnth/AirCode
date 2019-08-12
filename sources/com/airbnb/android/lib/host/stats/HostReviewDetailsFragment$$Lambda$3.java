package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$3 implements Predicate {
    private final HostReviewDetailsFragment arg$1;

    private HostReviewDetailsFragment$$Lambda$3(HostReviewDetailsFragment hostReviewDetailsFragment) {
        this.arg$1 = hostReviewDetailsFragment;
    }

    public static Predicate lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment) {
        return new HostReviewDetailsFragment$$Lambda$3(hostReviewDetailsFragment);
    }

    public boolean apply(Object obj) {
        return HostReviewDetailsFragment.lambda$onCreateView$0(this.arg$1, (Listing) obj);
    }
}
