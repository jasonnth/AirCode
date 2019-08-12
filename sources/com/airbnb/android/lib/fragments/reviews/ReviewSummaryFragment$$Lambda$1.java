package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;

final /* synthetic */ class ReviewSummaryFragment$$Lambda$1 implements OnClickListener {
    private final ReviewSummaryFragment arg$1;

    private ReviewSummaryFragment$$Lambda$1(ReviewSummaryFragment reviewSummaryFragment) {
        this.arg$1 = reviewSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(ReviewSummaryFragment reviewSummaryFragment) {
        return new ReviewSummaryFragment$$Lambda$1(reviewSummaryFragment);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1.getActivity(), this.arg$1.mReview.getRecipient().getId()));
    }
}
