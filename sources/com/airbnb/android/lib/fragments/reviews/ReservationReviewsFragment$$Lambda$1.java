package com.airbnb.android.lib.fragments.reviews;

import com.airbnb.android.core.responses.ReviewsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationReviewsFragment$$Lambda$1 implements Action1 {
    private final ReservationReviewsFragment arg$1;

    private ReservationReviewsFragment$$Lambda$1(ReservationReviewsFragment reservationReviewsFragment) {
        this.arg$1 = reservationReviewsFragment;
    }

    public static Action1 lambdaFactory$(ReservationReviewsFragment reservationReviewsFragment) {
        return new ReservationReviewsFragment$$Lambda$1(reservationReviewsFragment);
    }

    public void call(Object obj) {
        ReservationReviewsFragment.lambda$new$0(this.arg$1, (ReviewsResponse) obj);
    }
}
