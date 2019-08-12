package com.airbnb.android.core.adapters;

import com.google.common.base.Function;

final /* synthetic */ class HouseRulesEpoxyController$$Lambda$3 implements Function {
    private final HouseRulesEpoxyController arg$1;

    private HouseRulesEpoxyController$$Lambda$3(HouseRulesEpoxyController houseRulesEpoxyController) {
        this.arg$1 = houseRulesEpoxyController;
    }

    public static Function lambdaFactory$(HouseRulesEpoxyController houseRulesEpoxyController) {
        return new HouseRulesEpoxyController$$Lambda$3(houseRulesEpoxyController);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildRowModel(this.arg$1.context.getString(((Integer) obj).intValue()));
    }
}
