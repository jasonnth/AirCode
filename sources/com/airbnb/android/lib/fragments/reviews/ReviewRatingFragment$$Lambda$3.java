package com.airbnb.android.lib.fragments.reviews;

import android.widget.RatingBar.OnRatingBarChangeListener;

final /* synthetic */ class ReviewRatingFragment$$Lambda$3 implements Runnable {
    private final ReviewRatingFragment arg$1;
    private final OnRatingBarChangeListener arg$2;

    private ReviewRatingFragment$$Lambda$3(ReviewRatingFragment reviewRatingFragment, OnRatingBarChangeListener onRatingBarChangeListener) {
        this.arg$1 = reviewRatingFragment;
        this.arg$2 = onRatingBarChangeListener;
    }

    public static Runnable lambdaFactory$(ReviewRatingFragment reviewRatingFragment, OnRatingBarChangeListener onRatingBarChangeListener) {
        return new ReviewRatingFragment$$Lambda$3(reviewRatingFragment, onRatingBarChangeListener);
    }

    public void run() {
        ReviewRatingFragment.lambda$null$1(this.arg$1, this.arg$2);
    }
}
