package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.utils.ListingReviewsUtil;
import com.airbnb.p027n2.components.ReviewMarquee;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ReviewMarqueeEpoxyModel extends AirEpoxyModel<ReviewMarquee> {
    int numReviews;
    float numStars;

    public void bind(ReviewMarquee view) {
        super.bind(view);
        view.setNumStars(this.numStars);
        view.setTitle(ListingReviewsUtil.getNumReviewsText(view.getContext(), this.numReviews));
    }
}
