package com.airbnb.android.explore.fragments;

import android.view.View;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelClickListener;

final /* synthetic */ class ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$9 implements OnModelClickListener {
    private final FiltersAdapter arg$1;

    private ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$9(FiltersAdapter filtersAdapter) {
        this.arg$1 = filtersAdapter;
    }

    public static OnModelClickListener lambdaFactory$(FiltersAdapter filtersAdapter) {
        return new ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$9(filtersAdapter);
    }

    public void onClick(EpoxyModel epoxyModel, Object obj, View view, int i) {
        this.arg$1.setPriceFilterButtonSelection(1);
    }
}
