package com.airbnb.android.lib.fragments.reviews;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final /* synthetic */ class ReviewFeedbackFragment$$Lambda$1 implements OnGlobalLayoutListener {
    private final ReviewFeedbackFragment arg$1;

    private ReviewFeedbackFragment$$Lambda$1(ReviewFeedbackFragment reviewFeedbackFragment) {
        this.arg$1 = reviewFeedbackFragment;
    }

    public static OnGlobalLayoutListener lambdaFactory$(ReviewFeedbackFragment reviewFeedbackFragment) {
        return new ReviewFeedbackFragment$$Lambda$1(reviewFeedbackFragment);
    }

    public void onGlobalLayout() {
        ReviewFeedbackFragment.lambda$new$0(this.arg$1);
    }
}
