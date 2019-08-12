package com.airbnb.android.explore.adapters;

import android.view.View;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.p027n2.collections.BannerContainer.BannerClickListener;

final /* synthetic */ class BaseExploreAdapter$$Lambda$1 implements BannerClickListener {
    private final BaseExploreAdapter arg$1;
    private final ExploreSection arg$2;

    private BaseExploreAdapter$$Lambda$1(BaseExploreAdapter baseExploreAdapter, ExploreSection exploreSection) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = exploreSection;
    }

    public static BannerClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, ExploreSection exploreSection) {
        return new BaseExploreAdapter$$Lambda$1(baseExploreAdapter, exploreSection);
    }

    public void onBannerClicked(int i, View view, int i2) {
        this.arg$1.handleRecommendationItemClick((RecommendationItem) this.arg$2.getBanners().get(i), i2);
    }
}
