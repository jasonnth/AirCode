package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ExplorePromotion;
import com.airbnb.android.core.viewcomponents.models.InterstitialEpoxyModel_;

final /* synthetic */ class BaseExploreAdapter$$Lambda$9 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final ExplorePromotion arg$2;
    private final InterstitialEpoxyModel_ arg$3;

    private BaseExploreAdapter$$Lambda$9(BaseExploreAdapter baseExploreAdapter, ExplorePromotion explorePromotion, InterstitialEpoxyModel_ interstitialEpoxyModel_) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = explorePromotion;
        this.arg$3 = interstitialEpoxyModel_;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, ExplorePromotion explorePromotion, InterstitialEpoxyModel_ interstitialEpoxyModel_) {
        return new BaseExploreAdapter$$Lambda$9(baseExploreAdapter, explorePromotion, interstitialEpoxyModel_);
    }

    public void onClick(View view) {
        this.arg$1.handlePromotionCallToAction(this.arg$2, this.arg$3);
    }
}
