package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;

/* renamed from: com.airbnb.android.explore.fragments.ExploreExperiencesFiltersFragment$FiltersEpoxyController$$Lambda$4 */
final /* synthetic */ class C6345x9b91a90c implements OnCheckChangedListener {
    private final FiltersEpoxyController arg$1;
    private final PrimaryCategory arg$2;

    private C6345x9b91a90c(FiltersEpoxyController filtersEpoxyController, PrimaryCategory primaryCategory) {
        this.arg$1 = filtersEpoxyController;
        this.arg$2 = primaryCategory;
    }

    public static OnCheckChangedListener lambdaFactory$(FiltersEpoxyController filtersEpoxyController, PrimaryCategory primaryCategory) {
        return new C6345x9b91a90c(filtersEpoxyController, primaryCategory);
    }

    public void onCheckChanged(FindInlineFiltersToggleRow findInlineFiltersToggleRow, boolean z) {
        ExploreExperiencesFiltersFragment.this.searchFilters.setHasPrimaryCategory(this.arg$2, z);
    }
}
