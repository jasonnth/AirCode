package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;

public class ReservationCancellationPenaltyFreeTrialAdapter extends ReasonPickerAdapter {
    public ReservationCancellationPenaltyFreeTrialAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo, Reservation reservation) {
        super(callback, reservationCancellationInfo);
        addTitle(C0880R.string.penalties_are_waived_during_trial);
        Incentive incentive = reservation.getIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL);
        if (incentive != null) {
            addStandardRow(incentive.getDescription(), (String) null);
        }
        addModel(new LinkActionRowEpoxyModel_().textRes(C0880R.string.what_penalties_are_being_waived).clickListener(ReservationCancellationPenaltyFreeTrialAdapter$$Lambda$1.lambdaFactory$(callback)));
        addKeepReservationLink();
    }
}
