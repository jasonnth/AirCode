package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.content.Context;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.airbnb.epoxy.EpoxyModel;

public class ReservationCancellationReviewPenaltiesAdapter extends ReasonPickerAdapter {
    public ReservationCancellationReviewPenaltiesAdapter(ReasonPickerCallback callback, Context context, Reservation reservation, ReservationCancellationInfo reservationCancellationInfo, boolean isWaived, boolean isModal) {
        super(callback, reservationCancellationInfo, isModal);
        if (isWaived) {
            addTitleRes(C0880R.string.reservation_cancellation_waived_penalties_title);
        } else {
            addTitleRes(C0880R.string.reservation_cancellation_review_penalties_title);
        }
        addStandardRow(context.getString(C0880R.string.cancellation_penalties_fee_title), context.getString(C0880R.string.cancellation_penalties_fee, new Object[]{reservationCancellationInfo.getCancellationFeeInfo().getValue()}));
        addStandardRow(context.getString(C0880R.string.cancellation_penalties_blocked_calendar_title), context.getString(C0880R.string.cancellation_penalties_blocked_calendar_description, new Object[]{reservation.getStartDate().getDateSpanString(context, reservation.getEndDate())}));
        addStandardRow(context.getString(C0880R.string.cancellation_penalties_review_title), context.getString(C0880R.string.cancellation_penalties_review_description));
        addStandardRow(context.getString(C0880R.string.cancellation_penalties_superhost_status_title), context.getString(C0880R.string.cancellation_penalties_superhost_status_description));
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{new MicroRowEpoxyModel_().textRes(C0880R.string.cancellation_penalties_multiple_cancellations_note)});
        if (!isModal) {
            addKeepReservationLink();
        }
    }
}
