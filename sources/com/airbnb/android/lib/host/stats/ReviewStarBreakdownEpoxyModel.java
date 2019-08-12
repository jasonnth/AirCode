package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.HostRatingDistributionStatistic;
import com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView;
import com.airbnb.android.lib.host.stats.views.ReviewStarBreakdownView.Callback;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class ReviewStarBreakdownEpoxyModel extends AirEpoxyModel<ReviewStarBreakdownView> {
    Callback callback;
    List<HostRatingDistributionStatistic> ratingDistributionStatistics;

    public void bind(ReviewStarBreakdownView view) {
        super.bind(view);
        view.setReviewData(this.ratingDistributionStatistics);
        view.setCallback(this.callback);
    }

    /* access modifiers changed from: 0000 */
    public void setReviewData(List<HostRatingDistributionStatistic> ratingDistributionStatistics2, Callback callback2) {
        this.ratingDistributionStatistics = ratingDistributionStatistics2;
        this.callback = callback2;
    }
}
