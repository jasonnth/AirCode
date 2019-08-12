package com.airbnb.android.listyourspacedls.controllers;

import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class LYSNewHostDiscountController$$Lambda$1 implements OnCheckedChangeListener {
    private final LYSNewHostDiscountController arg$1;

    private LYSNewHostDiscountController$$Lambda$1(LYSNewHostDiscountController lYSNewHostDiscountController) {
        this.arg$1 = lYSNewHostDiscountController;
    }

    public static OnCheckedChangeListener lambdaFactory$(LYSNewHostDiscountController lYSNewHostDiscountController) {
        return new LYSNewHostDiscountController$$Lambda$1(lYSNewHostDiscountController);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        LYSNewHostDiscountController.lambda$buildModels$0(this.arg$1, toggleActionRow, z);
    }
}
