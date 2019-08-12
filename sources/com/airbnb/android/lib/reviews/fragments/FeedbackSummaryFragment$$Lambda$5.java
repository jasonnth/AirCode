package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FeedbackSummaryFragment$$Lambda$5 implements OnClickListener {
    private final FeedbackSummaryFragment arg$1;

    private FeedbackSummaryFragment$$Lambda$5(FeedbackSummaryFragment feedbackSummaryFragment) {
        this.arg$1 = feedbackSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(FeedbackSummaryFragment feedbackSummaryFragment) {
        return new FeedbackSummaryFragment$$Lambda$5(feedbackSummaryFragment);
    }

    public void onClick(View view) {
        this.arg$1.promptSubmitReview();
    }
}
