package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Review;

final /* synthetic */ class HostReservationObjectAdapter$$Lambda$1 implements OnClickListener {
    private final HostReservationObjectAdapter arg$1;
    private final Review arg$2;

    private HostReservationObjectAdapter$$Lambda$1(HostReservationObjectAdapter hostReservationObjectAdapter, Review review) {
        this.arg$1 = hostReservationObjectAdapter;
        this.arg$2 = review;
    }

    public static OnClickListener lambdaFactory$(HostReservationObjectAdapter hostReservationObjectAdapter, Review review) {
        return new HostReservationObjectAdapter$$Lambda$1(hostReservationObjectAdapter, review);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToReview(this.arg$2);
    }
}
