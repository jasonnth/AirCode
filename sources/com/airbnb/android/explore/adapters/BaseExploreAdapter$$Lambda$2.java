package com.airbnb.android.explore.adapters;

import android.view.View;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.explore.adapters.FilterRemovalSuggestionCarouselAdapter.CarouselItemClickListener;

final /* synthetic */ class BaseExploreAdapter$$Lambda$2 implements CarouselItemClickListener {
    private final BaseExploreAdapter arg$1;

    private BaseExploreAdapter$$Lambda$2(BaseExploreAdapter baseExploreAdapter) {
        this.arg$1 = baseExploreAdapter;
    }

    public static CarouselItemClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter) {
        return new BaseExploreAdapter$$Lambda$2(baseExploreAdapter);
    }

    public void onCarouselItemClicked(View view, FilterRemovalSuggestionItem filterRemovalSuggestionItem) {
        this.arg$1.onFilterRemovalSuggestionPillClicked(filterRemovalSuggestionItem);
    }
}
