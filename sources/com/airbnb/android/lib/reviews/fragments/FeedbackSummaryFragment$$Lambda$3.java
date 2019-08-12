package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FeedbackSummaryFragment$$Lambda$3 implements OnClickListener {
    private final FeedbackSummaryFragment arg$1;

    private FeedbackSummaryFragment$$Lambda$3(FeedbackSummaryFragment feedbackSummaryFragment) {
        this.arg$1 = feedbackSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(FeedbackSummaryFragment feedbackSummaryFragment) {
        return new FeedbackSummaryFragment$$Lambda$3(feedbackSummaryFragment);
    }

    public void onClick(View view) {
        FeedbackSummaryFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
