package com.airbnb.android.lib.host.stats;

import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$6 implements Action1 {
    private final HostReviewDetailsFragment arg$1;

    private HostReviewDetailsFragment$$Lambda$6(HostReviewDetailsFragment hostReviewDetailsFragment) {
        this.arg$1 = hostReviewDetailsFragment;
    }

    public static Action1 lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment) {
        return new HostReviewDetailsFragment$$Lambda$6(hostReviewDetailsFragment);
    }

    public void call(Object obj) {
        this.arg$1.listingsWithReviewScores = new ArrayList<>(((HostRecentReviewsPerListingResponse) obj).listings);
    }
}
