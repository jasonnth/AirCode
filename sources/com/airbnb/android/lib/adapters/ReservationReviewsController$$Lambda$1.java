package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Review;

final /* synthetic */ class ReservationReviewsController$$Lambda$1 implements OnClickListener {
    private final ReservationReviewsController arg$1;
    private final Review arg$2;

    private ReservationReviewsController$$Lambda$1(ReservationReviewsController reservationReviewsController, Review review) {
        this.arg$1 = reservationReviewsController;
        this.arg$2 = review;
    }

    public static OnClickListener lambdaFactory$(ReservationReviewsController reservationReviewsController, Review review) {
        return new ReservationReviewsController$$Lambda$1(reservationReviewsController, review);
    }

    public void onClick(View view) {
        this.arg$1.listener.toggleTranslationState(this.arg$2);
    }
}
