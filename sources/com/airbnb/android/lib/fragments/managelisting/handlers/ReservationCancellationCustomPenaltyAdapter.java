package com.airbnb.android.lib.fragments.managelisting.handlers;

import com.airbnb.android.core.models.IconWithTitles;
import com.airbnb.android.core.models.ReservationCancellationInfo;
import com.airbnb.android.lib.fragments.managelisting.handlers.ReasonPickerAdapter.ReasonPickerCallback;
import com.google.common.base.Joiner;

public class ReservationCancellationCustomPenaltyAdapter extends ReasonPickerAdapter {
    public ReservationCancellationCustomPenaltyAdapter(ReasonPickerCallback callback, ReservationCancellationInfo reservationCancellationInfo) {
        super(callback, reservationCancellationInfo);
        IconWithTitles iconWithTitles = (IconWithTitles) reservationCancellationInfo.getCustomCancellationPenaltyMobile().get(0);
        addTitle((CharSequence) iconWithTitles.getTitle());
        addStandardRow(Joiner.m1896on("\n").join((Iterable<?>) iconWithTitles.getSubtitles()), (String) null);
        addKeepReservationLink();
    }
}
