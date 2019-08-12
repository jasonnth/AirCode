package com.airbnb.android.explore.fragments;

import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;

/* renamed from: com.airbnb.android.explore.fragments.ExploreExperiencesFiltersFragment$FiltersEpoxyController$$Lambda$2 */
final /* synthetic */ class C6343x9b91a90a implements OnCheckChangedListener {
    private final FiltersEpoxyController arg$1;

    private C6343x9b91a90a(FiltersEpoxyController filtersEpoxyController) {
        this.arg$1 = filtersEpoxyController;
    }

    public static OnCheckChangedListener lambdaFactory$(FiltersEpoxyController filtersEpoxyController) {
        return new C6343x9b91a90a(filtersEpoxyController);
    }

    public void onCheckChanged(FindInlineFiltersToggleRow findInlineFiltersToggleRow, boolean z) {
        ExploreExperiencesFiltersFragment.this.searchFilters.setHasProductType(C6213ProductType.EXPERIENCE, z);
    }
}
