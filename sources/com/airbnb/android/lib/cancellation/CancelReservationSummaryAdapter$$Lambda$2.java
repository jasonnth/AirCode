package com.airbnb.android.lib.cancellation;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Price;

final /* synthetic */ class CancelReservationSummaryAdapter$$Lambda$2 implements OnClickListener {
    private final CancelReservationSummaryAdapter arg$1;
    private final Price arg$2;

    private CancelReservationSummaryAdapter$$Lambda$2(CancelReservationSummaryAdapter cancelReservationSummaryAdapter, Price price) {
        this.arg$1 = cancelReservationSummaryAdapter;
        this.arg$2 = price;
    }

    public static OnClickListener lambdaFactory$(CancelReservationSummaryAdapter cancelReservationSummaryAdapter, Price price) {
        return new CancelReservationSummaryAdapter$$Lambda$2(cancelReservationSummaryAdapter, price);
    }

    public void onClick(View view) {
        this.arg$1.listener.onNonRefundableItemLink(this.arg$2.getType());
    }
}
