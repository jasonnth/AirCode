package com.airbnb.android.explore.fragments;

import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;

/* renamed from: com.airbnb.android.explore.fragments.ExploreExperiencesFiltersFragment$FiltersEpoxyController$$Lambda$3 */
final /* synthetic */ class C6344x9b91a90b implements OnCheckChangedListener {
    private final FiltersEpoxyController arg$1;

    private C6344x9b91a90b(FiltersEpoxyController filtersEpoxyController) {
        this.arg$1 = filtersEpoxyController;
    }

    public static OnCheckChangedListener lambdaFactory$(FiltersEpoxyController filtersEpoxyController) {
        return new C6344x9b91a90b(filtersEpoxyController);
    }

    public void onCheckChanged(FindInlineFiltersToggleRow findInlineFiltersToggleRow, boolean z) {
        ExploreExperiencesFiltersFragment.this.searchFilters.setExperienceSocialGoodOnly(Boolean.valueOf(z));
    }
}
