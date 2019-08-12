package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.beta.models.guidebook.GuidebookItem;

final /* synthetic */ class BaseExploreAdapter$$Lambda$6 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final GuidebookItem arg$2;

    private BaseExploreAdapter$$Lambda$6(BaseExploreAdapter baseExploreAdapter, GuidebookItem guidebookItem) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = guidebookItem;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, GuidebookItem guidebookItem) {
        return new BaseExploreAdapter$$Lambda$6(baseExploreAdapter, guidebookItem);
    }

    public void onClick(View view) {
        BaseExploreAdapter.lambda$buildGuidebookItemModel$5(this.arg$1, this.arg$2, view);
    }
}
