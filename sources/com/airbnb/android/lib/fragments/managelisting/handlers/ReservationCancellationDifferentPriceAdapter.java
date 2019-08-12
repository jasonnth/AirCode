package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationDifferentPriceAdapter extends ReasonPickerAdapter {
    public ReservationCancellationDifferentPriceAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo) {
        super(callback, reservationCancellationInfo);
        addTitleRes(C0880R.string.different_trip_price_title);
        addReasons(ReservationCancellationReason.getSubReasons(ReservationCancellationReason.PriceOrTripLength));
        addKeepReservationLink();
    }
}
