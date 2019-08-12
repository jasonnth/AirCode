package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.ListingReviewsUtil;
import com.airbnb.p027n2.components.StarRatingSummary;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class StarRatingSummaryEpoxyModel extends AirEpoxyModel<StarRatingSummary> {
    OnClickListener clickListener;
    Integer minNumReviewsToShowStars;
    int reviewsCount;
    float starRating;
    String title;

    public void bind(StarRatingSummary view) {
        super.bind(view);
        if (!TextUtils.isEmpty(this.title)) {
            view.setTitle((CharSequence) this.title);
        } else {
            view.setTitle(ListingReviewsUtil.getNumReviewsText(view.getContext(), this.reviewsCount));
        }
        if (this.minNumReviewsToShowStars != null) {
            view.setUpRatingBar(this.reviewsCount, this.starRating, this.minNumReviewsToShowStars.intValue());
        } else {
            view.setUpRatingBar(this.reviewsCount, this.starRating);
        }
        view.setOnClickListener(this.clickListener);
    }

    public void unbind(StarRatingSummary view) {
        super.unbind(view);
        view.setOnClickListener(null);
    }

    public StarRatingSummaryEpoxyModel listing(Listing listing) {
        this.reviewsCount = listing.getReviewsCount();
        this.starRating = listing.getStarRating();
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
