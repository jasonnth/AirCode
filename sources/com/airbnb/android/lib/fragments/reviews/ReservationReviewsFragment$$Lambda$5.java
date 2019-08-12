package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReservationReviewsFragment$$Lambda$5 implements OnClickListener {
    private final ReservationReviewsFragment arg$1;

    private ReservationReviewsFragment$$Lambda$5(ReservationReviewsFragment reservationReviewsFragment) {
        this.arg$1 = reservationReviewsFragment;
    }

    public static OnClickListener lambdaFactory$(ReservationReviewsFragment reservationReviewsFragment) {
        return new ReservationReviewsFragment$$Lambda$5(reservationReviewsFragment);
    }

    public void onClick(View view) {
        this.arg$1.loadMoreReviews();
    }
}
