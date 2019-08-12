package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

final /* synthetic */ class ReservationCancellationPenaltyFreeTrialAdapter$$Lambda$1 implements OnClickListener {
    private final ReasonPickerCallback arg$1;

    private ReservationCancellationPenaltyFreeTrialAdapter$$Lambda$1(ReasonPickerCallback reasonPickerCallback) {
        this.arg$1 = reasonPickerCallback;
    }

    public static OnClickListener lambdaFactory$(ReasonPickerCallback reasonPickerCallback) {
        return new ReservationCancellationPenaltyFreeTrialAdapter$$Lambda$1(reasonPickerCallback);
    }

    public void onClick(View view) {
        this.arg$1.onShowModal(ReservationCancellationReason.ReviewPenalties);
    }
}
