package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;
import p032rx.functions.Action1;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$8 implements Action1 {
    private final HostReviewDetailsFragment arg$1;
    private final Listing arg$2;

    private HostReviewDetailsFragment$$Lambda$8(HostReviewDetailsFragment hostReviewDetailsFragment, Listing listing) {
        this.arg$1 = hostReviewDetailsFragment;
        this.arg$2 = listing;
    }

    public static Action1 lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment, Listing listing) {
        return new HostReviewDetailsFragment$$Lambda$8(hostReviewDetailsFragment, listing);
    }

    public void call(Object obj) {
        HostReviewDetailsFragment.lambda$loadListingCategoryRatings$5(this.arg$1, this.arg$2, (HostRatingBreakdownResponse) obj);
    }
}
