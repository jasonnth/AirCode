package com.airbnb.android.cohosting.shared;

import com.airbnb.android.cohosting.epoxycontrollers.CohostingPaymentsBaseEpoxyController;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CohostingPaymentsSection$$Lambda$6 implements OnCheckedChangeListener {
    private final CohostingPaymentsBaseEpoxyController arg$1;

    private CohostingPaymentsSection$$Lambda$6(CohostingPaymentsBaseEpoxyController cohostingPaymentsBaseEpoxyController) {
        this.arg$1 = cohostingPaymentsBaseEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(CohostingPaymentsBaseEpoxyController cohostingPaymentsBaseEpoxyController) {
        return new CohostingPaymentsSection$$Lambda$6(cohostingPaymentsBaseEpoxyController);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.enableCleaningFee(z);
    }
}