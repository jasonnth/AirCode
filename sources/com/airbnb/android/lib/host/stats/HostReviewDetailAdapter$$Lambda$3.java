package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Review;

final /* synthetic */ class HostReviewDetailAdapter$$Lambda$3 implements OnClickListener {
    private final Review arg$1;

    private HostReviewDetailAdapter$$Lambda$3(Review review) {
        this.arg$1 = review;
    }

    public static OnClickListener lambdaFactory$(Review review) {
        return new HostReviewDetailAdapter$$Lambda$3(review);
    }

    public void onClick(View view) {
        HostReviewDetailAdapter.lambda$reviewToEpoxyModel$1(this.arg$1, view);
    }
}
