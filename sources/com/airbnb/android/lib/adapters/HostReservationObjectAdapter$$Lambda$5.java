package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.ReviewsMode;

final /* synthetic */ class HostReservationObjectAdapter$$Lambda$5 implements OnClickListener {
    private final HostReservationObjectAdapter arg$1;

    private HostReservationObjectAdapter$$Lambda$5(HostReservationObjectAdapter hostReservationObjectAdapter) {
        this.arg$1 = hostReservationObjectAdapter;
    }

    public static OnClickListener lambdaFactory$(HostReservationObjectAdapter hostReservationObjectAdapter) {
        return new HostReservationObjectAdapter$$Lambda$5(hostReservationObjectAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.goToGuestReviewsModal(ReviewsMode.MODE_GUEST);
    }
}
