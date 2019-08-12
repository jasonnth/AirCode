package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.ReservationCancellationReason;

final /* synthetic */ class ReasonPickerAdapter$$Lambda$1 implements OnClickListener {
    private final ReasonPickerAdapter arg$1;
    private final ReservationCancellationReason arg$2;

    private ReasonPickerAdapter$$Lambda$1(ReasonPickerAdapter reasonPickerAdapter, ReservationCancellationReason reservationCancellationReason) {
        this.arg$1 = reasonPickerAdapter;
        this.arg$2 = reservationCancellationReason;
    }

    public static OnClickListener lambdaFactory$(ReasonPickerAdapter reasonPickerAdapter, ReservationCancellationReason reservationCancellationReason) {
        return new ReasonPickerAdapter$$Lambda$1(reasonPickerAdapter, reservationCancellationReason);
    }

    public void onClick(View view) {
        this.arg$1.callback.onReasonClicked(this.arg$2, false);
    }
}
