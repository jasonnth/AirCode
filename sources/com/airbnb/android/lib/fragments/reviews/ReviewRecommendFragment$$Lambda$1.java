package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReviewRecommendFragment$$Lambda$1 implements OnClickListener {
    private final ReviewRecommendFragment arg$1;

    private ReviewRecommendFragment$$Lambda$1(ReviewRecommendFragment reviewRecommendFragment) {
        this.arg$1 = reviewRecommendFragment;
    }

    public static OnClickListener lambdaFactory$(ReviewRecommendFragment reviewRecommendFragment) {
        return new ReviewRecommendFragment$$Lambda$1(reviewRecommendFragment);
    }

    public void onClick(View view) {
        ReviewRecommendFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
