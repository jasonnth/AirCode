package com.airbnb.android.explore.fragments;

import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow.OnCheckChangedListener;

final /* synthetic */ class ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$3 implements OnCheckChangedListener {
    private final FiltersAdapter arg$1;

    private ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$3(FiltersAdapter filtersAdapter) {
        this.arg$1 = filtersAdapter;
    }

    public static OnCheckChangedListener lambdaFactory$(FiltersAdapter filtersAdapter) {
        return new ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$3(filtersAdapter);
    }

    public void onCheckChanged(FindInlineFiltersToggleRow findInlineFiltersToggleRow, boolean z) {
        FiltersAdapter.lambda$new$2(this.arg$1, findInlineFiltersToggleRow, z);
    }
}
