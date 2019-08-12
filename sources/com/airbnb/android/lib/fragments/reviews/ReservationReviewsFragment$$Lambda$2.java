package com.airbnb.android.lib.fragments.reviews;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationReviewsFragment$$Lambda$2 implements Action1 {
    private final ReservationReviewsFragment arg$1;

    private ReservationReviewsFragment$$Lambda$2(ReservationReviewsFragment reservationReviewsFragment) {
        this.arg$1 = reservationReviewsFragment;
    }

    public static Action1 lambdaFactory$(ReservationReviewsFragment reservationReviewsFragment) {
        return new ReservationReviewsFragment$$Lambda$2(reservationReviewsFragment);
    }

    public void call(Object obj) {
        ReservationReviewsFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
