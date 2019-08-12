package com.airbnb.android.explore.adapters;

import android.view.View;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.viewcomponents.viewmodels.FilterSuggestionPillEpoxyModel_;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.List;

public class FilterSuggestionCarouselController extends TypedAirEpoxyController<List<FilterSuggestionItem>> {
    private final CarouselClickListener carouselClickListener;

    public interface CarouselClickListener {
        void onCarouselItemClicked(View view, FilterSuggestionItem filterSuggestionItem);
    }

    public FilterSuggestionCarouselController(CarouselClickListener carouselClickListener2) {
        this.carouselClickListener = carouselClickListener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<FilterSuggestionItem> filters) {
        for (FilterSuggestionItem filter : filters) {
            new FilterSuggestionPillEpoxyModel_().m5917id((CharSequence) filter.getDisplayText()).item(filter).clickListener(FilterSuggestionCarouselController$$Lambda$1.lambdaFactory$(this, filter)).addTo(this);
        }
    }
}
