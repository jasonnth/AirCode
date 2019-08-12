package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.content.Context;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.FilterRemovalSuggestionPill;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class FilterRemovalPillEpoxyModel extends AirEpoxyModel<FilterRemovalSuggestionPill> {
    OnClickListener clickListener;
    FilterRemovalSuggestionItem item;

    public void bind(FilterRemovalSuggestionPill suggestionPill) {
        super.bind(suggestionPill);
        suggestionPill.setMaxWidth(Carousel.getCarouselCardWidth(suggestionPill.getContext(), 1));
        int filterStringRes = getFilterStringRes(suggestionPill.getContext(), this.item);
        if (filterStringRes != 0) {
            suggestionPill.setFilterText(filterStringRes);
        }
        suggestionPill.setOnClickListener(this.clickListener);
    }

    private int getFilterStringRes(Context context, FilterRemovalSuggestionItem item2) {
        switch (item2.getType()) {
            case InstantBook:
                return C0857R.string.instant_book;
            case Amenity:
                Amenity amenity = Amenity.forId(item2.getId());
                if (amenity != null) {
                    return amenity.stringRes;
                }
                return 0;
            default:
                return 0;
        }
    }

    public void unbind(FilterRemovalSuggestionPill suggestionPill) {
        super.unbind(suggestionPill);
        suggestionPill.setOnClickListener(null);
    }

    public int getDividerViewType() {
        return -1;
    }
}
