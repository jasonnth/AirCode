package com.airbnb.android.p011p3;

import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.ReviewsController$$Lambda$7 */
final /* synthetic */ class ReviewsController$$Lambda$7 implements Action1 {
    private final ReviewsController arg$1;

    private ReviewsController$$Lambda$7(ReviewsController reviewsController) {
        this.arg$1 = reviewsController;
    }

    public static Action1 lambdaFactory$(ReviewsController reviewsController) {
        return new ReviewsController$$Lambda$7(reviewsController);
    }

    public void call(Object obj) {
        this.arg$1.p3Controller.showErrorSnackbar(C7532R.string.p3_translation_error);
    }
}
