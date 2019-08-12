package com.airbnb.android.contentframework.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.contentframework.views.StoryCreationPickTripRowView;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StoryCreationPickTripRowEpoxyModel extends AirEpoxyModel<StoryCreationPickTripRowView> {
    OnClickListener clickListener;
    Reservation reservation;

    public void bind(StoryCreationPickTripRowView view) {
        super.bind(view);
        view.setImageUrl(this.reservation.getCityPhotoUrl());
        view.setTitle(this.reservation.getListing().getLocation());
        view.setSubtitle(this.reservation.getStartDate().getDateString(view.getContext()));
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(StoryCreationPickTripRowView view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }
}
