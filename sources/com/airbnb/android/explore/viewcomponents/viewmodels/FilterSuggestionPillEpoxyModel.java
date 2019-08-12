package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.FilterSuggestionPill;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class FilterSuggestionPillEpoxyModel extends AirEpoxyModel<FilterSuggestionPill> {
    OnClickListener clickListener;
    FilterSuggestionItem item;

    public void bind(FilterSuggestionPill suggestionPill) {
        super.bind(suggestionPill);
        suggestionPill.setMaxWidth(Carousel.getCarouselCardWidth(suggestionPill.getContext(), 1));
        suggestionPill.setFilterText((CharSequence) this.item.getDisplayText());
        suggestionPill.setOnClickListener(this.clickListener);
    }

    public void unbind(FilterSuggestionPill suggestionPill) {
        super.unbind(suggestionPill);
        suggestionPill.setOnClickListener(null);
    }
}
