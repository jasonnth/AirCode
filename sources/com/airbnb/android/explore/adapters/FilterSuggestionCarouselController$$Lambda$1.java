package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterSuggestionItem;

final /* synthetic */ class FilterSuggestionCarouselController$$Lambda$1 implements OnClickListener {
    private final FilterSuggestionCarouselController arg$1;
    private final FilterSuggestionItem arg$2;

    private FilterSuggestionCarouselController$$Lambda$1(FilterSuggestionCarouselController filterSuggestionCarouselController, FilterSuggestionItem filterSuggestionItem) {
        this.arg$1 = filterSuggestionCarouselController;
        this.arg$2 = filterSuggestionItem;
    }

    public static OnClickListener lambdaFactory$(FilterSuggestionCarouselController filterSuggestionCarouselController, FilterSuggestionItem filterSuggestionItem) {
        return new FilterSuggestionCarouselController$$Lambda$1(filterSuggestionCarouselController, filterSuggestionItem);
    }

    public void onClick(View view) {
        this.arg$1.carouselClickListener.onCarouselItemClicked(view, this.arg$2);
    }
}
