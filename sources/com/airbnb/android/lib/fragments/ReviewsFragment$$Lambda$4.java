package com.airbnb.android.lib.fragments;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final /* synthetic */ class ReviewsFragment$$Lambda$4 implements OnCheckedChangeListener {
    private final ReviewsFragment arg$1;

    private ReviewsFragment$$Lambda$4(ReviewsFragment reviewsFragment) {
        this.arg$1 = reviewsFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(ReviewsFragment reviewsFragment) {
        return new ReviewsFragment$$Lambda$4(reviewsFragment);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ReviewsFragment.lambda$translationsAreAvailable$3(this.arg$1, compoundButton, z);
    }
}
