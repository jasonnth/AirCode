package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ExploreSection;

final /* synthetic */ class BaseExploreAdapter$$Lambda$4 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final ExploreSection arg$2;

    private BaseExploreAdapter$$Lambda$4(BaseExploreAdapter baseExploreAdapter, ExploreSection exploreSection) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = exploreSection;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, ExploreSection exploreSection) {
        return new BaseExploreAdapter$$Lambda$4(baseExploreAdapter, exploreSection);
    }

    public void onClick(View view) {
        BaseExploreAdapter.lambda$getSeeAllOnClickListener$3(this.arg$1, this.arg$2, view);
    }
}
