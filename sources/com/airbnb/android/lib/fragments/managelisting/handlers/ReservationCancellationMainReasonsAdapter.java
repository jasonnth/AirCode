package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationMainReasonsAdapter extends ReasonPickerAdapter {
    public ReservationCancellationMainReasonsAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo, Reservation reservation, boolean showUncomfortableWithGuest) {
        super(callback, reservationCancellationInfo);
        addTitleRes(C0880R.string.reservation_cancellation_title);
        addReasons(ReservationCancellationReason.getMainReasons(showUncomfortableWithGuest));
        addKeepReservationLink();
    }
}
