package com.airbnb.android.lib.fragments.reviews;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import p032rx.functions.Action1;

final /* synthetic */ class ReservationReviewsFragment$$Lambda$4 implements Action1 {
    private final ReservationReviewsFragment arg$1;

    private ReservationReviewsFragment$$Lambda$4(ReservationReviewsFragment reservationReviewsFragment) {
        this.arg$1 = reservationReviewsFragment;
    }

    public static Action1 lambdaFactory$(ReservationReviewsFragment reservationReviewsFragment) {
        return new ReservationReviewsFragment$$Lambda$4(reservationReviewsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj, C0880R.string.p3_translation_error, C0880R.string.p3_translation_error);
    }
}
