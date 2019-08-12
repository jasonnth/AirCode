package com.airbnb.android.explore.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterSuggestionItem;
import java.util.List;

final /* synthetic */ class CategorizedFiltersCarouselController$$Lambda$2 implements OnClickListener {
    private final CategorizedFiltersCarouselController arg$1;
    private final List arg$2;

    private CategorizedFiltersCarouselController$$Lambda$2(CategorizedFiltersCarouselController categorizedFiltersCarouselController, List list) {
        this.arg$1 = categorizedFiltersCarouselController;
        this.arg$2 = list;
    }

    public static OnClickListener lambdaFactory$(CategorizedFiltersCarouselController categorizedFiltersCarouselController, List list) {
        return new CategorizedFiltersCarouselController$$Lambda$2(categorizedFiltersCarouselController, list);
    }

    public void onClick(View view) {
        this.arg$1.carouselClickListener.onCarouselItemClicked(view, (FilterSuggestionItem) this.arg$2.get(1));
    }
}
