package com.airbnb.android.lib.reviews.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class WriteReviewActivity$$Lambda$2 implements Action1 {
    private final WriteReviewActivity arg$1;

    private WriteReviewActivity$$Lambda$2(WriteReviewActivity writeReviewActivity) {
        this.arg$1 = writeReviewActivity;
    }

    public static Action1 lambdaFactory$(WriteReviewActivity writeReviewActivity) {
        return new WriteReviewActivity$$Lambda$2(writeReviewActivity);
    }

    public void call(Object obj) {
        WriteReviewActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
