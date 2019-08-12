package com.airbnb.android.lib.fragments.reviews;

import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

final /* synthetic */ class ReviewRatingFragment$$Lambda$1 implements OnRatingBarChangeListener {
    private final ReviewRatingFragment arg$1;

    private ReviewRatingFragment$$Lambda$1(ReviewRatingFragment reviewRatingFragment) {
        this.arg$1 = reviewRatingFragment;
    }

    public static OnRatingBarChangeListener lambdaFactory$(ReviewRatingFragment reviewRatingFragment) {
        return new ReviewRatingFragment$$Lambda$1(reviewRatingFragment);
    }

    public void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        ReviewRatingFragment.lambda$initializeViews$0(this.arg$1, ratingBar, f, z);
    }
}
