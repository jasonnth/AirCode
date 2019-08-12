package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationMissedEarningsAdapter extends ReasonPickerAdapter {
    public ReservationCancellationMissedEarningsAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo, boolean hideCancellationFee) {
        super(callback, reservationCancellationInfo);
        addTitle(C0880R.string.cancellation_penalties_missed_earnings_title);
        addStandardRow(reservationCancellationInfo.getMissedEarningsInfo());
        if (!hideCancellationFee) {
            addStandardRow(reservationCancellationInfo.getCancellationFeeInfo());
        }
        addKeepReservationLink();
    }
}
