package com.airbnb.android.cohosting.shared;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentsBaseEpoxyController;

final /* synthetic */ class CohostingPaymentsSection$$Lambda$2 implements OnClickListener {
    private final CohostingPaymentsBaseEpoxyController arg$1;

    private CohostingPaymentsSection$$Lambda$2(CohostingPaymentsBaseEpoxyController cohostingPaymentsBaseEpoxyController) {
        this.arg$1 = cohostingPaymentsBaseEpoxyController;
    }

    public static OnClickListener lambdaFactory$(CohostingPaymentsBaseEpoxyController cohostingPaymentsBaseEpoxyController) {
        return new CohostingPaymentsSection$$Lambda$2(cohostingPaymentsBaseEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.choosePaymentType();
    }
}
