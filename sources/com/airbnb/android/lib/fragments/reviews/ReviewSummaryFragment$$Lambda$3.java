package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReviewSummaryFragment$$Lambda$3 implements OnClickListener {
    private final ReviewSummaryFragment arg$1;

    private ReviewSummaryFragment$$Lambda$3(ReviewSummaryFragment reviewSummaryFragment) {
        this.arg$1 = reviewSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(ReviewSummaryFragment reviewSummaryFragment) {
        return new ReviewSummaryFragment$$Lambda$3(reviewSummaryFragment);
    }

    public void onClick(View view) {
        ReviewSummaryFragment.lambda$initializeViews$2(this.arg$1, view);
    }
}
