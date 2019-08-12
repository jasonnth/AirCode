package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationReachPenaltyLimitAdapter extends ReasonPickerAdapter {
    public ReservationCancellationReachPenaltyLimitAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo) {
        super(callback, reservationCancellationInfo);
        addTitle(C0880R.string.reservation_cancellation_reach_penalty_limit_title);
        addStandardRow(C0880R.string.reservation_cancellation_reach_penalty_limit_subtitle, 10);
        addKeepReservationLink();
    }
}
