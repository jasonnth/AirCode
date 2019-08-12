package com.airbnb.android.lib.cancellation;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CancelReservationSummaryAdapter$$Lambda$1 implements OnClickListener {
    private final CancelReservationSummaryAdapter arg$1;

    private CancelReservationSummaryAdapter$$Lambda$1(CancelReservationSummaryAdapter cancelReservationSummaryAdapter) {
        this.arg$1 = cancelReservationSummaryAdapter;
    }

    public static OnClickListener lambdaFactory$(CancelReservationSummaryAdapter cancelReservationSummaryAdapter) {
        return new CancelReservationSummaryAdapter$$Lambda$1(cancelReservationSummaryAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.onClickPolicyLink();
    }
}
