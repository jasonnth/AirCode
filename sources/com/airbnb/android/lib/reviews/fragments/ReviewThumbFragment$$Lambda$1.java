package com.airbnb.android.lib.reviews.fragments;

final /* synthetic */ class ReviewThumbFragment$$Lambda$1 implements Runnable {
    private final ReviewThumbFragment arg$1;

    private ReviewThumbFragment$$Lambda$1(ReviewThumbFragment reviewThumbFragment) {
        this.arg$1 = reviewThumbFragment;
    }

    public static Runnable lambdaFactory$(ReviewThumbFragment reviewThumbFragment) {
        return new ReviewThumbFragment$$Lambda$1(reviewThumbFragment);
    }

    public void run() {
        this.arg$1.wrInterface.showFragment(new FeedbackSummaryFragment());
    }
}
