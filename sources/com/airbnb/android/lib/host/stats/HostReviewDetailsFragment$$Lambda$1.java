package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.responses.ReviewsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$1 implements Action1 {
    private final HostReviewDetailsFragment arg$1;

    private HostReviewDetailsFragment$$Lambda$1(HostReviewDetailsFragment hostReviewDetailsFragment) {
        this.arg$1 = hostReviewDetailsFragment;
    }

    public static Action1 lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment) {
        return new HostReviewDetailsFragment$$Lambda$1(hostReviewDetailsFragment);
    }

    public void call(Object obj) {
        HostReviewDetailsFragment.lambda$new$13(this.arg$1, (ReviewsResponse) obj);
    }
}
