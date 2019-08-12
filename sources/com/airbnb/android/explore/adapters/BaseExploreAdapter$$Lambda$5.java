package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.TripTemplate;

final /* synthetic */ class BaseExploreAdapter$$Lambda$5 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final TripTemplate arg$2;

    private BaseExploreAdapter$$Lambda$5(BaseExploreAdapter baseExploreAdapter, TripTemplate tripTemplate) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = tripTemplate;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, TripTemplate tripTemplate) {
        return new BaseExploreAdapter$$Lambda$5(baseExploreAdapter, tripTemplate);
    }

    public void onClick(View view) {
        this.arg$1.getPosterCardClickListener(view, this.arg$2);
    }
}
