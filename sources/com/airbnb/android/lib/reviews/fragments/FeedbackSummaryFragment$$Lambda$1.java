package com.airbnb.android.lib.reviews.fragments;

import com.airbnb.android.core.responses.ReviewResponse;
import p032rx.functions.Action1;

final /* synthetic */ class FeedbackSummaryFragment$$Lambda$1 implements Action1 {
    private final FeedbackSummaryFragment arg$1;

    private FeedbackSummaryFragment$$Lambda$1(FeedbackSummaryFragment feedbackSummaryFragment) {
        this.arg$1 = feedbackSummaryFragment;
    }

    public static Action1 lambdaFactory$(FeedbackSummaryFragment feedbackSummaryFragment) {
        return new FeedbackSummaryFragment$$Lambda$1(feedbackSummaryFragment);
    }

    public void call(Object obj) {
        FeedbackSummaryFragment.lambda$new$3(this.arg$1, (ReviewResponse) obj);
    }
}
