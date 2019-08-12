package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.p008mt.models.ExplorePlacesTopCategory;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;

final /* synthetic */ class ExplorePlacesFiltersFragment$FiltersAdapter$$Lambda$1 implements OnCheckChangedListener {
    private final FiltersAdapter arg$1;
    private final ExplorePlacesTopCategory arg$2;

    private ExplorePlacesFiltersFragment$FiltersAdapter$$Lambda$1(FiltersAdapter filtersAdapter, ExplorePlacesTopCategory explorePlacesTopCategory) {
        this.arg$1 = filtersAdapter;
        this.arg$2 = explorePlacesTopCategory;
    }

    public static OnCheckChangedListener lambdaFactory$(FiltersAdapter filtersAdapter, ExplorePlacesTopCategory explorePlacesTopCategory) {
        return new ExplorePlacesFiltersFragment$FiltersAdapter$$Lambda$1(filtersAdapter, explorePlacesTopCategory);
    }

    public void onCheckChanged(FindInlineFiltersToggleRow findInlineFiltersToggleRow, boolean z) {
        ExplorePlacesFiltersFragment.this.placesSearchFilters.setHasFilter(this.arg$2, z);
    }
}
