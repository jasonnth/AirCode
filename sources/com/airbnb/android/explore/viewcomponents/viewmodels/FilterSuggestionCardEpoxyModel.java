package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController.CarouselClickListener;
import com.airbnb.android.explore.views.FilterSuggestionCard;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

public abstract class FilterSuggestionCardEpoxyModel extends AirEpoxyModel<FilterSuggestionCard> {
    CarouselClickListener carouselItemClickListener;
    List<FilterSuggestionItem> items;
    CharSequence title;

    public void bind(FilterSuggestionCard view) {
        super.bind(view);
        view.setup(this.title, this.items, this.carouselItemClickListener);
    }

    public int getDividerViewType() {
        return 0;
    }

    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }
}
