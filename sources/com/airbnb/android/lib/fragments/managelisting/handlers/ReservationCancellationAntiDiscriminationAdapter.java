package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.content.Context;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationAntiDiscriminationAdapter extends ReasonPickerAdapter {
    public ReservationCancellationAntiDiscriminationAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo, Context context, User guest) {
        super(callback, reservationCancellationInfo);
        addTitle(C0880R.string.cancellation_anti_discrimination_title);
        addStandardRow(C0880R.string.cancellation_anti_discrimination, 10);
        addViewNondiscriminationPolicyLink();
        addKeepReservationLink();
    }
}
