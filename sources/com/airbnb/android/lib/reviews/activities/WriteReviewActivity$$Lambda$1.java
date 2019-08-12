package com.airbnb.android.lib.reviews.activities;

import com.airbnb.android.core.responses.ReviewResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WriteReviewActivity$$Lambda$1 implements Action1 {
    private final WriteReviewActivity arg$1;

    private WriteReviewActivity$$Lambda$1(WriteReviewActivity writeReviewActivity) {
        this.arg$1 = writeReviewActivity;
    }

    public static Action1 lambdaFactory$(WriteReviewActivity writeReviewActivity) {
        return new WriteReviewActivity$$Lambda$1(writeReviewActivity);
    }

    public void call(Object obj) {
        this.arg$1.setReview(((ReviewResponse) obj).review);
    }
}
