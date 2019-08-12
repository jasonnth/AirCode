package com.airbnb.android.lib.activities.reviews;

final /* synthetic */ class ReviewRatingsActivity$$Lambda$2 implements Runnable {
    private final ReviewRatingsActivity arg$1;

    private ReviewRatingsActivity$$Lambda$2(ReviewRatingsActivity reviewRatingsActivity) {
        this.arg$1 = reviewRatingsActivity;
    }

    public static Runnable lambdaFactory$(ReviewRatingsActivity reviewRatingsActivity) {
        return new ReviewRatingsActivity$$Lambda$2(reviewRatingsActivity);
    }

    public void run() {
        ReviewRatingsActivity.lambda$onRatingUpdated$1(this.arg$1);
    }
}
