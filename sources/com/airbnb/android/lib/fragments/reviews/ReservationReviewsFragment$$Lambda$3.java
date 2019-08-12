package com.airbnb.android.lib.fragments.reviews;

import com.airbnb.android.core.responses.TranslateReviewsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationReviewsFragment$$Lambda$3 implements Action1 {
    private final ReservationReviewsFragment arg$1;

    private ReservationReviewsFragment$$Lambda$3(ReservationReviewsFragment reservationReviewsFragment) {
        this.arg$1 = reservationReviewsFragment;
    }

    public static Action1 lambdaFactory$(ReservationReviewsFragment reservationReviewsFragment) {
        return new ReservationReviewsFragment$$Lambda$3(reservationReviewsFragment);
    }

    public void call(Object obj) {
        ReservationReviewsFragment.lambda$new$3(this.arg$1, (TranslateReviewsResponse) obj);
    }
}
