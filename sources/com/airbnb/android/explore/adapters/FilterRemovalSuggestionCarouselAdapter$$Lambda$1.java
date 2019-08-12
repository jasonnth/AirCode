package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;

final /* synthetic */ class FilterRemovalSuggestionCarouselAdapter$$Lambda$1 implements OnClickListener {
    private final FilterRemovalSuggestionCarouselAdapter arg$1;
    private final FilterRemovalSuggestionItem arg$2;

    private FilterRemovalSuggestionCarouselAdapter$$Lambda$1(FilterRemovalSuggestionCarouselAdapter filterRemovalSuggestionCarouselAdapter, FilterRemovalSuggestionItem filterRemovalSuggestionItem) {
        this.arg$1 = filterRemovalSuggestionCarouselAdapter;
        this.arg$2 = filterRemovalSuggestionItem;
    }

    public static OnClickListener lambdaFactory$(FilterRemovalSuggestionCarouselAdapter filterRemovalSuggestionCarouselAdapter, FilterRemovalSuggestionItem filterRemovalSuggestionItem) {
        return new FilterRemovalSuggestionCarouselAdapter$$Lambda$1(filterRemovalSuggestionCarouselAdapter, filterRemovalSuggestionItem);
    }

    public void onClick(View view) {
        this.arg$1.carouselClickListener.onCarouselItemClicked(view, this.arg$2);
    }
}
