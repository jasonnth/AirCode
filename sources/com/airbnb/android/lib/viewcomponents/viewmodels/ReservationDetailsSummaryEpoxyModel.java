package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Reservation;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ReservationDetailsSummaryEpoxyModel extends AirEpoxyModel<UserDetailsActionRow> {
    Reservation reservation;

    public void bind(UserDetailsActionRow row) {
        super.bind(row);
        row.setTitleText(this.reservation.getGuest().getName());
        row.setSubtitleText(this.reservation.getCheckoutTime().getElapsedTime(row.getContext()));
        row.setExtraText(this.reservation.getListing().getName());
        row.setUserImageUrl(this.reservation.getGuest().getPictureUrl());
    }

    public int getDividerViewType() {
        return 0;
    }
}
