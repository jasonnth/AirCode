package com.airbnb.android.lib.adapters;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;

final /* synthetic */ class ReservationReviewsController$$Lambda$2 implements OnModelBoundListener {
    private final ReservationReviewsController arg$1;

    private ReservationReviewsController$$Lambda$2(ReservationReviewsController reservationReviewsController) {
        this.arg$1 = reservationReviewsController;
    }

    public static OnModelBoundListener lambdaFactory$(ReservationReviewsController reservationReviewsController) {
        return new ReservationReviewsController$$Lambda$2(reservationReviewsController);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        this.arg$1.listener.loadMoreReviews();
    }
}
