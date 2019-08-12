package com.airbnb.android.lib.fragments.reviews;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RatingBar.OnRatingBarChangeListener;

final /* synthetic */ class ReviewRatingFragment$$Lambda$2 implements OnTouchListener {
    private final ReviewRatingFragment arg$1;
    private final OnRatingBarChangeListener arg$2;

    private ReviewRatingFragment$$Lambda$2(ReviewRatingFragment reviewRatingFragment, OnRatingBarChangeListener onRatingBarChangeListener) {
        this.arg$1 = reviewRatingFragment;
        this.arg$2 = onRatingBarChangeListener;
    }

    public static OnTouchListener lambdaFactory$(ReviewRatingFragment reviewRatingFragment, OnRatingBarChangeListener onRatingBarChangeListener) {
        return new ReviewRatingFragment$$Lambda$2(reviewRatingFragment, onRatingBarChangeListener);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return ReviewRatingFragment.lambda$initializeViews$2(this.arg$1, this.arg$2, view, motionEvent);
    }
}
