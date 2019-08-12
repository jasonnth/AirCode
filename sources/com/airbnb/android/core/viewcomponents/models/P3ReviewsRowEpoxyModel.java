package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.P3ReviewsRow;
import com.airbnb.p027n2.components.HomeReviewRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.List;

public abstract class P3ReviewsRowEpoxyModel extends AirEpoxyModel<P3ReviewsRow> {
    OnClickListener clickListener;
    boolean loading;
    List<HomeReviewRowEpoxyModel_> reviewModels;
    int reviewsCount;
    float starRating;

    public void bind(P3ReviewsRow view) {
        boolean z;
        super.bind(view);
        view.setLoading(this.loading);
        HomeReviewRow lastVisibleReview = null;
        int i = 0;
        while (i < view.getNumReviewsSupported()) {
            HomeReviewRowEpoxyModel reviewModel = i < this.reviewModels.size() ? (HomeReviewRowEpoxyModel) this.reviewModels.get(i) : null;
            if (i > 0 && reviewModel != null) {
                reviewModel = null;
            }
            HomeReviewRow reviewRow = view.getReviewRow(i);
            reviewRow.showDivider(true);
            if (reviewModel != null) {
                z = true;
            } else {
                z = false;
            }
            ViewLibUtils.setVisibleIf(reviewRow, z);
            if (reviewModel != null) {
                lastVisibleReview = reviewRow;
                reviewModel.bind(reviewRow);
            }
            i++;
        }
        if (lastVisibleReview != null) {
            lastVisibleReview.showDivider(false);
        }
        view.setInputText(view.getResources().getQuantityString(C0716R.plurals.read_reviews, this.reviewsCount, new Object[]{Integer.valueOf(this.reviewsCount)}));
        view.setHeaderText(C0716R.string.p3_reviews_section_header);
        view.setReadAllReviewsClickListener(this.clickListener);
        view.setUpRatingBar(this.reviewsCount, this.starRating);
    }

    public void unbind(P3ReviewsRow view) {
        int i = 0;
        while (i < view.getNumReviewsSupported()) {
            HomeReviewRowEpoxyModel reviewModel = i < this.reviewModels.size() ? (HomeReviewRowEpoxyModel) this.reviewModels.get(i) : null;
            if (reviewModel != null) {
                reviewModel.unbind(view.getReviewRow(i));
            }
            i++;
        }
    }

    public int getDividerViewType() {
        return 0;
    }
}
