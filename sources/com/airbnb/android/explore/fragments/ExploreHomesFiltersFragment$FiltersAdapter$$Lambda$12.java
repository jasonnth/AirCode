package com.airbnb.android.explore.fragments;

import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar.OnRangeSeekBarChangeListener;

final /* synthetic */ class ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$12 implements OnRangeSeekBarChangeListener {
    private final FiltersAdapter arg$1;

    private ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$12(FiltersAdapter filtersAdapter) {
        this.arg$1 = filtersAdapter;
    }

    public static OnRangeSeekBarChangeListener lambdaFactory$(FiltersAdapter filtersAdapter) {
        return new ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$12(filtersAdapter);
    }

    public void onRangeSeekBarValuesChanged(ExploreBaseRangeSeekBar exploreBaseRangeSeekBar, Object obj, Object obj2, boolean z) {
        FiltersAdapter.lambda$new$11(this.arg$1, exploreBaseRangeSeekBar, (Integer) obj, (Integer) obj2, z);
    }
}
