package com.airbnb.android.cohosting.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class CohostingShareEarningsAdapter$$Lambda$1 implements Listener {
    private final CohostingShareEarningsAdapter arg$1;

    private CohostingShareEarningsAdapter$$Lambda$1(CohostingShareEarningsAdapter cohostingShareEarningsAdapter) {
        this.arg$1 = cohostingShareEarningsAdapter;
    }

    public static Listener lambdaFactory$(CohostingShareEarningsAdapter cohostingShareEarningsAdapter) {
        return new CohostingShareEarningsAdapter$$Lambda$1(cohostingShareEarningsAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.updateShareButtonAvailability();
    }
}
