package com.airbnb.android.lib.reviews.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReviewStarFragment$$Lambda$1 implements OnClickListener {
    private final ReviewStarFragment arg$1;

    private ReviewStarFragment$$Lambda$1(ReviewStarFragment reviewStarFragment) {
        this.arg$1 = reviewStarFragment;
    }

    public static OnClickListener lambdaFactory$(ReviewStarFragment reviewStarFragment) {
        return new ReviewStarFragment$$Lambda$1(reviewStarFragment);
    }

    public void onClick(View view) {
        this.arg$1.goToNextRating();
    }
}
