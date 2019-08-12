package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;

final /* synthetic */ class ReviewFeedbackFragment$$Lambda$2 implements OnClickListener {
    private final ReviewFeedbackFragment arg$1;

    private ReviewFeedbackFragment$$Lambda$2(ReviewFeedbackFragment reviewFeedbackFragment) {
        this.arg$1 = reviewFeedbackFragment;
    }

    public static OnClickListener lambdaFactory$(ReviewFeedbackFragment reviewFeedbackFragment) {
        return new ReviewFeedbackFragment$$Lambda$2(reviewFeedbackFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1.getActivity(), this.arg$1.mReview.getRecipient().getId()));
    }
}
