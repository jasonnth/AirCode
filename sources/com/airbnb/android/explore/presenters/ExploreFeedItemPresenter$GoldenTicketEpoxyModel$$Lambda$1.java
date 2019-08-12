package com.airbnb.android.explore.presenters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.presenters.ExploreFeedItemPresenter.GoldenTicketEpoxyModel;
import com.airbnb.android.explore.presenters.ExploreFeedItemPresenter.OnInlineFilterItemClickListener;

final /* synthetic */ class ExploreFeedItemPresenter$GoldenTicketEpoxyModel$$Lambda$1 implements OnClickListener {
    private final ExploreJitneyLogger arg$1;
    private final InlineSearchFeedFilterItem arg$2;
    private final OnInlineFilterItemClickListener arg$3;

    private ExploreFeedItemPresenter$GoldenTicketEpoxyModel$$Lambda$1(ExploreJitneyLogger exploreJitneyLogger, InlineSearchFeedFilterItem inlineSearchFeedFilterItem, OnInlineFilterItemClickListener onInlineFilterItemClickListener) {
        this.arg$1 = exploreJitneyLogger;
        this.arg$2 = inlineSearchFeedFilterItem;
        this.arg$3 = onInlineFilterItemClickListener;
    }

    public static OnClickListener lambdaFactory$(ExploreJitneyLogger exploreJitneyLogger, InlineSearchFeedFilterItem inlineSearchFeedFilterItem, OnInlineFilterItemClickListener onInlineFilterItemClickListener) {
        return new ExploreFeedItemPresenter$GoldenTicketEpoxyModel$$Lambda$1(exploreJitneyLogger, inlineSearchFeedFilterItem, onInlineFilterItemClickListener);
    }

    public void onClick(View view) {
        GoldenTicketEpoxyModel.lambda$new$0(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
