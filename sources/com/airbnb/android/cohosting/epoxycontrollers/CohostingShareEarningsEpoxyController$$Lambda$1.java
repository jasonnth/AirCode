package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.OnModelBoundListener;

final /* synthetic */ class CohostingShareEarningsEpoxyController$$Lambda$1 implements OnModelBoundListener {
    private final CohostingShareEarningsEpoxyController arg$1;

    private CohostingShareEarningsEpoxyController$$Lambda$1(CohostingShareEarningsEpoxyController cohostingShareEarningsEpoxyController) {
        this.arg$1 = cohostingShareEarningsEpoxyController;
    }

    public static OnModelBoundListener lambdaFactory$(CohostingShareEarningsEpoxyController cohostingShareEarningsEpoxyController) {
        return new CohostingShareEarningsEpoxyController$$Lambda$1(cohostingShareEarningsEpoxyController);
    }

    public void onModelBound(EpoxyModel epoxyModel, Object obj, int i) {
        this.arg$1.logger.logContractExistAlertImpression(this.arg$1.controller.getCohostingContext(), null);
    }
}
