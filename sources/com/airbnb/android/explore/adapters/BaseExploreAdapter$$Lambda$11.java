package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.SavedSearch;

final /* synthetic */ class BaseExploreAdapter$$Lambda$11 implements OnClickListener {
    private final BaseExploreAdapter arg$1;
    private final SavedSearch arg$2;

    private BaseExploreAdapter$$Lambda$11(BaseExploreAdapter baseExploreAdapter, SavedSearch savedSearch) {
        this.arg$1 = baseExploreAdapter;
        this.arg$2 = savedSearch;
    }

    public static OnClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter, SavedSearch savedSearch) {
        return new BaseExploreAdapter$$Lambda$11(baseExploreAdapter, savedSearch);
    }

    public void onClick(View view) {
        this.arg$1.dataController.updateFromSavedSearch(C5809SearchInputType.SavedSearch, this.arg$2);
    }
}
