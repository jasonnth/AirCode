package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;

final /* synthetic */ class ReviewsFragment$$Lambda$1 implements OnItemLongClickListener {
    private final ReviewsFragment arg$1;

    private ReviewsFragment$$Lambda$1(ReviewsFragment reviewsFragment) {
        this.arg$1 = reviewsFragment;
    }

    public static OnItemLongClickListener lambdaFactory$(ReviewsFragment reviewsFragment) {
        return new ReviewsFragment$$Lambda$1(reviewsFragment);
    }

    public boolean onItemLongClick(AdapterView adapterView, View view, int i, long j) {
        return ReviewsFragment.lambda$onCreateView$0(this.arg$1, adapterView, view, i, j);
    }
}
