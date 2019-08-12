package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.ReservationCancellationWithUserInputController;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationKnowMoreAdapter extends ReasonPickerAdapter {
    public ReservationCancellationKnowMoreAdapter(ReasonPickerCallback reasonPickerCallback, ReservationCancellationInfo reservationCancellationInfo, ReservationCancellationWithUserInputController explanationCallback, String explanationString) {
        super(reasonPickerCallback, reservationCancellationInfo);
        addTitleRes(C0880R.string.reservation_cancellation_know_more_title, 0);
        addModel(new StandardRowEpoxyModel_().title(C0880R.string.reservation_cancellation_know_more_reason).titleMaxLine(2).subtitle((CharSequence) explanationString).actionText(explanationString == null ? C0880R.string.add : C0880R.string.edit).clickListener(ReservationCancellationKnowMoreAdapter$$Lambda$1.lambdaFactory$(explanationCallback, explanationString)));
        addKeepReservationLink();
    }
}
