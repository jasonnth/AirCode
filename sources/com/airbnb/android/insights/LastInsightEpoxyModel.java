package com.airbnb.android.insights;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class LastInsightEpoxyModel extends AirEpoxyModel<LastInsightView> {
    Insight dummyInsight;
    boolean isLoading;
    Listing nextListing;
    OnClickListener primaryButtonClickListener;

    public void bind(LastInsightView view) {
        super.bind(view);
        view.setPrimaryButtonClickListener(this.primaryButtonClickListener);
        view.setPrimaryButtonLoading(this.isLoading);
        if (this.nextListing != null) {
            view.setTitle(C6552R.string.insight_more_insights_title);
            view.setDescription(C6552R.string.insight_more_insights_description);
            if (!this.isLoading) {
                view.setPrimaryButtonText(C6552R.string.action_card_next_listing);
                return;
            }
            return;
        }
        view.setTitle(C6552R.string.insight_no_more_insights_title);
        view.setDescription(C6552R.string.insight_no_more_insights_description);
        view.setPrimaryButtonText(C6552R.string.action_card_okay);
    }
}
