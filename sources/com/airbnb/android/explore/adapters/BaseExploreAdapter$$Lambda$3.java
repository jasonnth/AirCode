package com.airbnb.android.explore.adapters;

import android.view.View;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController.CarouselClickListener;

final /* synthetic */ class BaseExploreAdapter$$Lambda$3 implements CarouselClickListener {
    private final BaseExploreAdapter arg$1;

    private BaseExploreAdapter$$Lambda$3(BaseExploreAdapter baseExploreAdapter) {
        this.arg$1 = baseExploreAdapter;
    }

    public static CarouselClickListener lambdaFactory$(BaseExploreAdapter baseExploreAdapter) {
        return new BaseExploreAdapter$$Lambda$3(baseExploreAdapter);
    }

    public void onCarouselItemClicked(View view, FilterSuggestionItem filterSuggestionItem) {
        this.arg$1.onFilterSuggestionPillClicked(filterSuggestionItem);
    }
}
