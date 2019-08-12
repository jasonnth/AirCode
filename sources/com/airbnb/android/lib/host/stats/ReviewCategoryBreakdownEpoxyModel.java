package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.HostRatingBreakdown;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ReviewCategoryBreakdownEpoxyModel extends AirEpoxyModel<ReviewCategoryBreakdownView> {
    HostRatingBreakdown ratingBreakdown;

    public void bind(ReviewCategoryBreakdownView view) {
        super.bind(view);
        view.setRatingBreakdown(this.ratingBreakdown);
    }

    public void setRatingBreakdown(HostRatingBreakdown ratingBreakdown2) {
        this.ratingBreakdown = ratingBreakdown2;
    }
}
