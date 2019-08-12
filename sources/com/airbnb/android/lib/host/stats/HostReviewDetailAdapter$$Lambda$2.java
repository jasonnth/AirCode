package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Review;
import com.google.common.base.Function;

final /* synthetic */ class HostReviewDetailAdapter$$Lambda$2 implements Function {
    private final HostReviewDetailAdapter arg$1;

    private HostReviewDetailAdapter$$Lambda$2(HostReviewDetailAdapter hostReviewDetailAdapter) {
        this.arg$1 = hostReviewDetailAdapter;
    }

    public static Function lambdaFactory$(HostReviewDetailAdapter hostReviewDetailAdapter) {
        return new HostReviewDetailAdapter$$Lambda$2(hostReviewDetailAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.reviewToEpoxyModel((Review) obj);
    }
}
