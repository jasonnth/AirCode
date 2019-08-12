package com.airbnb.android.p011p3;

import com.airbnb.android.core.responses.TranslateReviewsResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.p3.ReviewsController$$Lambda$6 */
final /* synthetic */ class ReviewsController$$Lambda$6 implements Action1 {
    private final ReviewsController arg$1;

    private ReviewsController$$Lambda$6(ReviewsController reviewsController) {
        this.arg$1 = reviewsController;
    }

    public static Action1 lambdaFactory$(ReviewsController reviewsController) {
        return new ReviewsController$$Lambda$6(reviewsController);
    }

    public void call(Object obj) {
        ReviewsController.lambda$new$5(this.arg$1, (TranslateReviewsResponse) obj);
    }
}
