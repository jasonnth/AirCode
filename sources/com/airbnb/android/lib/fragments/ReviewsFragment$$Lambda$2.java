package com.airbnb.android.lib.fragments;

import android.widget.ListView;
import com.airbnb.erf.ExperimentBuilder.Treatment;

final /* synthetic */ class ReviewsFragment$$Lambda$2 implements Treatment {
    private final ReviewsFragment arg$1;
    private final ListView arg$2;

    private ReviewsFragment$$Lambda$2(ReviewsFragment reviewsFragment, ListView listView) {
        this.arg$1 = reviewsFragment;
        this.arg$2 = listView;
    }

    public static Treatment lambdaFactory$(ReviewsFragment reviewsFragment, ListView listView) {
        return new ReviewsFragment$$Lambda$2(reviewsFragment, listView);
    }

    public void apply() {
        this.arg$1.showSubreviewStarsAsHeader(this.arg$2);
    }
}
