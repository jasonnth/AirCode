package com.airbnb.android.lib.host.stats;

import android.view.View;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.host.stats.HostRecentListingReviewsCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.lib.host.stats.HostReviewDetailAdapter.Callback;

final /* synthetic */ class HostReviewDetailAdapter$$Lambda$1 implements CarouselItemClickListener {
    private final HostReviewDetailAdapter arg$1;
    private final Callback arg$2;

    private HostReviewDetailAdapter$$Lambda$1(HostReviewDetailAdapter hostReviewDetailAdapter, Callback callback) {
        this.arg$1 = hostReviewDetailAdapter;
        this.arg$2 = callback;
    }

    public static CarouselItemClickListener lambdaFactory$(HostReviewDetailAdapter hostReviewDetailAdapter, Callback callback) {
        return new HostReviewDetailAdapter$$Lambda$1(hostReviewDetailAdapter, callback);
    }

    public void onCarouselItemClicked(View view, Listing listing, int i) {
        HostReviewDetailAdapter.lambda$new$0(this.arg$1, this.arg$2, view, listing, i);
    }
}
