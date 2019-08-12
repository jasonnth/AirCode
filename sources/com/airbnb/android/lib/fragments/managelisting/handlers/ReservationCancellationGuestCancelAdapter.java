package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.content.Context;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationGuestCancelAdapter extends ReasonPickerAdapter {
    public ReservationCancellationGuestCancelAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo, Context context, User guest) {
        super(callback, reservationCancellationInfo);
        addTitleRes(C0880R.string.reservation_cancellation_ask_guest_title);
        addModel(new StandardRowEpoxyModel_().title((CharSequence) context.getString(C0880R.string.reservation_cancellation_ask_guest_penalty, new Object[]{guest.getFirstName()})).titleMaxLine(15));
        addKeepReservationLink();
    }
}
