package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Review;

final /* synthetic */ class FeedbackExitFragment$$Lambda$1 implements OnClickListener {
    private final FeedbackExitFragment arg$1;
    private final boolean arg$2;
    private final Review arg$3;

    private FeedbackExitFragment$$Lambda$1(FeedbackExitFragment feedbackExitFragment, boolean z, Review review) {
        this.arg$1 = feedbackExitFragment;
        this.arg$2 = z;
        this.arg$3 = review;
    }

    public static OnClickListener lambdaFactory$(FeedbackExitFragment feedbackExitFragment, boolean z, Review review) {
        return new FeedbackExitFragment$$Lambda$1(feedbackExitFragment, z, review);
    }

    public void onClick(View view) {
        FeedbackExitFragment.lambda$onCreateView$0(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
