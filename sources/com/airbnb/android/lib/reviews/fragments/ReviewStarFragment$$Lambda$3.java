package com.airbnb.android.lib.reviews.fragments;

final /* synthetic */ class ReviewStarFragment$$Lambda$3 implements Runnable {
    private final ReviewStarFragment arg$1;

    private ReviewStarFragment$$Lambda$3(ReviewStarFragment reviewStarFragment) {
        this.arg$1 = reviewStarFragment;
    }

    public static Runnable lambdaFactory$(ReviewStarFragment reviewStarFragment) {
        return new ReviewStarFragment$$Lambda$3(reviewStarFragment);
    }

    public void run() {
        this.arg$1.goToNextRating();
    }
}
