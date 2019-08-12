package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.GuestRatingsMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class GuestRatingsMarqueeEpoxyModel extends AirEpoxyModel<GuestRatingsMarquee> {
    String guestName;
    float numStars;

    public void bind(GuestRatingsMarquee view) {
        super.bind(view);
        view.setNumStars(this.numStars);
        if (this.guestName == null) {
            view.getContext().getString(C0716R.string.guest_ratings_guest_name_placeholder);
        }
        view.setTitle(view.getContext().getString(C0716R.string.guest_ratings_title, new Object[]{this.guestName}));
    }
}
