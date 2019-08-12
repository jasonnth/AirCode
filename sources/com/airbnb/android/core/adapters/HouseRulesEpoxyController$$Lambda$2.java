package com.airbnb.android.core.adapters;

import com.airbnb.android.core.models.ListingExpectation;
import com.google.common.base.Function;

final /* synthetic */ class HouseRulesEpoxyController$$Lambda$2 implements Function {
    private final HouseRulesEpoxyController arg$1;

    private HouseRulesEpoxyController$$Lambda$2(HouseRulesEpoxyController houseRulesEpoxyController) {
        this.arg$1 = houseRulesEpoxyController;
    }

    public static Function lambdaFactory$(HouseRulesEpoxyController houseRulesEpoxyController) {
        return new HouseRulesEpoxyController$$Lambda$2(houseRulesEpoxyController);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildExpectationRowModel((ListingExpectation) obj);
    }
}
