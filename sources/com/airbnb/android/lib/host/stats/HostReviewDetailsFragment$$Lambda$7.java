package com.airbnb.android.lib.host.stats;

import p032rx.functions.Action1;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$7 implements Action1 {
    private final HostReviewDetailsFragment arg$1;

    private HostReviewDetailsFragment$$Lambda$7(HostReviewDetailsFragment hostReviewDetailsFragment) {
        this.arg$1 = hostReviewDetailsFragment;
    }

    public static Action1 lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment) {
        return new HostReviewDetailsFragment$$Lambda$7(hostReviewDetailsFragment);
    }

    public void call(Object obj) {
        HostReviewDetailsFragment.lambda$constructReviewDetailsRequestForBatch$4(this.arg$1, (HostReviewDetailsResponse) obj);
    }
}
