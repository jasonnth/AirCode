package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReviewsAdapter$$Lambda$1 implements OnClickListener {
    private final ReviewsAdapter arg$1;

    private ReviewsAdapter$$Lambda$1(ReviewsAdapter reviewsAdapter) {
        this.arg$1 = reviewsAdapter;
    }

    public static OnClickListener lambdaFactory$(ReviewsAdapter reviewsAdapter) {
        return new ReviewsAdapter$$Lambda$1(reviewsAdapter);
    }

    public void onClick(View view) {
        ReviewsAdapter.lambda$getClickListener$0(this.arg$1, view);
    }
}
