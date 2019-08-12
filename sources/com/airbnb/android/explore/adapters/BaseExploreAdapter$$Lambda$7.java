package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.RecommendationItem;
import java.util.List;

final /* synthetic */ class BaseExploreAdapter$$Lambda$7 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final List arg$2;

    private BaseExploreAdapter$$Lambda$7(BaseExploreAdapter baseExploreAdapter, List list) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = list;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, List list) {
        return new BaseExploreAdapter$$Lambda$7(baseExploreAdapter, list);
    }

    public void onClick(View view) {
        this.arg$1.handleRecommendationItemClick((RecommendationItem) this.arg$2.get(((Integer) view.getTag()).intValue()));
    }
}
