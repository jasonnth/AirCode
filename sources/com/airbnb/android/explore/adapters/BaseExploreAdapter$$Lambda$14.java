package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Destination;

final /* synthetic */ class BaseExploreAdapter$$Lambda$14 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final Destination arg$2;

    private BaseExploreAdapter$$Lambda$14(BaseExploreAdapter baseExploreAdapter, Destination destination) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = destination;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, Destination destination) {
        return new BaseExploreAdapter$$Lambda$14(baseExploreAdapter, destination);
    }

    public void onClick(View view) {
        this.arg$1.dataController.onDestinationClicked(this.arg$2.getQueryName());
    }
}
