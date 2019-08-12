package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.RecommendationItem;

final /* synthetic */ class BaseExploreAdapter$$Lambda$8 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final RecommendationItem arg$2;

    private BaseExploreAdapter$$Lambda$8(BaseExploreAdapter baseExploreAdapter, RecommendationItem recommendationItem) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = recommendationItem;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, RecommendationItem recommendationItem) {
        return new BaseExploreAdapter$$Lambda$8(baseExploreAdapter, recommendationItem);
    }

    public void onClick(View view) {
        this.arg$1.handleRecommendationItemClick(this.arg$2);
    }
}
