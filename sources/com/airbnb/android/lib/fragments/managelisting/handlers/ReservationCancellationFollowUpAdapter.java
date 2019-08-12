package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import java.util.Arrays;

public class ReservationCancellationFollowUpAdapter extends ReasonPickerAdapter {
    public ReservationCancellationFollowUpAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo) {
        super(callback, reservationCancellationInfo);
        addTitleRes(C0880R.string.post_reservation_cancellation_title, C0880R.string.post_reservation_cancellation_subtitle);
        addReasons(Arrays.asList(new ReservationCancellationReason[]{ReservationCancellationReason.GoodGuest, ReservationCancellationReason.ClearExpecation}));
    }
}
