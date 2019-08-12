package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReviewFeedbackFragment$$Lambda$3 implements OnClickListener {
    private final ReviewFeedbackFragment arg$1;

    private ReviewFeedbackFragment$$Lambda$3(ReviewFeedbackFragment reviewFeedbackFragment) {
        this.arg$1 = reviewFeedbackFragment;
    }

    public static OnClickListener lambdaFactory$(ReviewFeedbackFragment reviewFeedbackFragment) {
        return new ReviewFeedbackFragment$$Lambda$3(reviewFeedbackFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTooltip();
    }
}
