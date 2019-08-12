package com.airbnb.android.core.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HouseRulesEpoxyController$$Lambda$1 implements OnClickListener {
    private final HouseRulesEpoxyController arg$1;

    private HouseRulesEpoxyController$$Lambda$1(HouseRulesEpoxyController houseRulesEpoxyController) {
        this.arg$1 = houseRulesEpoxyController;
    }

    public static OnClickListener lambdaFactory$(HouseRulesEpoxyController houseRulesEpoxyController) {
        return new HouseRulesEpoxyController$$Lambda$1(houseRulesEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.onTranslateClicked(view);
    }
}
