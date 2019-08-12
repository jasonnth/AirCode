package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FeedbackIntroFragment$$Lambda$1 implements OnClickListener {
    private final FeedbackIntroFragment arg$1;

    private FeedbackIntroFragment$$Lambda$1(FeedbackIntroFragment feedbackIntroFragment) {
        this.arg$1 = feedbackIntroFragment;
    }

    public static OnClickListener lambdaFactory$(FeedbackIntroFragment feedbackIntroFragment) {
        return new FeedbackIntroFragment$$Lambda$1(feedbackIntroFragment);
    }

    public void onClick(View view) {
        FeedbackIntroFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
