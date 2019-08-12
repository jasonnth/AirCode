package com.airbnb.android.explore.adapters;

import android.view.View;
import com.airbnb.android.core.models.CategorizedFilters;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.viewcomponents.viewmodels.CategorizedFilterBarSpacerEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.CategorizedFilterButtonsEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import java.util.Collection;
import java.util.List;

public class CategorizedFiltersCarouselController extends TypedAirEpoxyController<List<CategorizedFilters>> {
    private final CarouselClickListener carouselClickListener;

    public interface CarouselClickListener {
        void onCarouselItemClicked(View view, FilterSuggestionItem filterSuggestionItem);
    }

    public CategorizedFiltersCarouselController(CarouselClickListener carouselClickListener2) {
        this.carouselClickListener = carouselClickListener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<CategorizedFilters> data) {
        new CategorizedFilterBarSpacerEpoxyModel_().m5821id((CharSequence) "filters_button_spacer").addTo(this);
        for (CategorizedFilters filters : data) {
            List<FilterSuggestionItem> items = filters.getItems();
            if (!ListUtils.isEmpty((Collection<?>) items)) {
                CategorizedFilterButtonsEpoxyModel_ model = new CategorizedFilterButtonsEpoxyModel_().m5833id((CharSequence) filters.getType().type).title(filters.getTitle()).item1((FilterSuggestionItem) items.get(0)).item1ClickListener(CategorizedFiltersCarouselController$$Lambda$1.lambdaFactory$(this, items));
                if (items.size() > 1) {
                    model.item2((FilterSuggestionItem) items.get(1)).item2ClickListener(CategorizedFiltersCarouselController$$Lambda$2.lambdaFactory$(this, items));
                }
                if (items.size() > 2) {
                    model.item3((FilterSuggestionItem) items.get(2)).item3ClickListener(CategorizedFiltersCarouselController$$Lambda$3.lambdaFactory$(this, items));
                }
                model.addTo(this);
            }
        }
    }
}
