package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.models.Amenity;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;

final /* synthetic */ class ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$13 implements OnCheckChangedListener {
    private final FiltersAdapter arg$1;
    private final Amenity arg$2;

    private ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$13(FiltersAdapter filtersAdapter, Amenity amenity) {
        this.arg$1 = filtersAdapter;
        this.arg$2 = amenity;
    }

    public static OnCheckChangedListener lambdaFactory$(FiltersAdapter filtersAdapter, Amenity amenity) {
        return new ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$13(filtersAdapter, amenity);
    }

    public void onCheckChanged(FindInlineFiltersToggleRow findInlineFiltersToggleRow, boolean z) {
        FiltersAdapter.lambda$buildAmenityEpoxyModel$12(this.arg$1, this.arg$2, findInlineFiltersToggleRow, z);
    }
}
