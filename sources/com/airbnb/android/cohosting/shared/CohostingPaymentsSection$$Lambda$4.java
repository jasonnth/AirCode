package com.airbnb.android.cohosting.shared;

import com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentsBaseEpoxyController;
import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class CohostingPaymentsSection$$Lambda$4 implements Listener {
    private final CohostingPaymentsBaseEpoxyController arg$1;

    private CohostingPaymentsSection$$Lambda$4(CohostingPaymentsBaseEpoxyController cohostingPaymentsBaseEpoxyController) {
        this.arg$1 = cohostingPaymentsBaseEpoxyController;
    }

    public static Listener lambdaFactory$(CohostingPaymentsBaseEpoxyController cohostingPaymentsBaseEpoxyController) {
        return new CohostingPaymentsSection$$Lambda$4(cohostingPaymentsBaseEpoxyController);
    }

    public void amountChanged(Integer num) {
        this.arg$1.changeMinimumFee(num);
    }
}
