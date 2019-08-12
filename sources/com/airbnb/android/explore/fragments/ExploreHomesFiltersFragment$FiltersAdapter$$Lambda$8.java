package com.airbnb.android.explore.fragments;

import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

final /* synthetic */ class ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$8 implements OnValueChangedListener {
    private final FiltersAdapter arg$1;

    private ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$8(FiltersAdapter filtersAdapter) {
        this.arg$1 = filtersAdapter;
    }

    public static OnValueChangedListener lambdaFactory$(FiltersAdapter filtersAdapter) {
        return new ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$8(filtersAdapter);
    }

    public void onValueChanged(int i, int i2) {
        FiltersAdapter.lambda$new$7(this.arg$1, i, i2);
    }
}
