package com.airbnb.android.lib.reviews.fragments;

import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

final /* synthetic */ class ReviewStarFragment$$Lambda$2 implements OnRatingBarChangeListener {
    private final ReviewStarFragment arg$1;

    private ReviewStarFragment$$Lambda$2(ReviewStarFragment reviewStarFragment) {
        this.arg$1 = reviewStarFragment;
    }

    public static OnRatingBarChangeListener lambdaFactory$(ReviewStarFragment reviewStarFragment) {
        return new ReviewStarFragment$$Lambda$2(reviewStarFragment);
    }

    public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        ReviewStarFragment.lambda$onCreateView$1(this.arg$1, ratingBar, f, z);
    }
}
