package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.content.Context;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.ReservationCancellationWithUserInputController;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationConfirmationInputAdapter extends ReasonPickerAdapter {
    public ReservationCancellationConfirmationInputAdapter(ReasonPickerCallback reasonPickerCallback, ReservationCancellationInfo reservationCancellationInfo, ReservationCancellationWithUserInputController editUserInputCallback, String explanationString, Context context, User guest) {
        super(reasonPickerCallback, reservationCancellationInfo);
        addTitleRes(C0880R.string.reservation_cancellation_confirmation_input_title, 0);
        addModel(new StandardRowEpoxyModel_().title((CharSequence) context.getString(C0880R.string.reservation_cancellation_confrimation_input_hint, new Object[]{guest.getFirstName()})).titleMaxLine(2).subtitle((CharSequence) explanationString).actionText(explanationString == null ? C0880R.string.add : C0880R.string.edit).clickListener(ReservationCancellationConfirmationInputAdapter$$Lambda$1.lambdaFactory$(editUserInputCallback, explanationString)));
        addKeepReservationLink();
    }
}
