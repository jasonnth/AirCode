package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class CohostingPaymentTypeEpoxyController$$Lambda$1 implements OnCheckedChangeListener {
    private final CohostingPaymentTypeEpoxyController arg$1;

    private CohostingPaymentTypeEpoxyController$$Lambda$1(CohostingPaymentTypeEpoxyController cohostingPaymentTypeEpoxyController) {
        this.arg$1 = cohostingPaymentTypeEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(CohostingPaymentTypeEpoxyController cohostingPaymentTypeEpoxyController) {
        return new CohostingPaymentTypeEpoxyController$$Lambda$1(cohostingPaymentTypeEpoxyController);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        this.arg$1.setPaymentType(FeeType.Percent);
    }
}
