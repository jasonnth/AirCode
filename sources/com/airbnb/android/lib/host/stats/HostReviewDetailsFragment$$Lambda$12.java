package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.responses.ReviewsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$12 implements Action1 {
    private final HostReviewDetailsFragment arg$1;
    private final long arg$2;

    private HostReviewDetailsFragment$$Lambda$12(HostReviewDetailsFragment hostReviewDetailsFragment, long j) {
        this.arg$1 = hostReviewDetailsFragment;
        this.arg$2 = j;
    }

    public static Action1 lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment, long j) {
        return new HostReviewDetailsFragment$$Lambda$12(hostReviewDetailsFragment, j);
    }

    public void call(Object obj) {
        HostReviewDetailsFragment.lambda$loadReviewsForListingWithOffset$11(this.arg$1, this.arg$2, (ReviewsResponse) obj);
    }
}
