package com.airbnb.android.explore.fragments;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$1 implements OnCheckedChangeListener {
    private final FiltersAdapter arg$1;

    private ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$1(FiltersAdapter filtersAdapter) {
        this.arg$1 = filtersAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(FiltersAdapter filtersAdapter) {
        return new ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$1(filtersAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        FiltersAdapter.lambda$new$0(this.arg$1, switchRowInterface, z);
    }
}
