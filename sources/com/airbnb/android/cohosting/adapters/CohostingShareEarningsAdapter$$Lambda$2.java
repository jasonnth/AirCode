package com.airbnb.android.cohosting.adapters;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CohostingShareEarningsAdapter$$Lambda$2 implements OnCheckedChangeListener {
    private final CohostingShareEarningsAdapter arg$1;

    private CohostingShareEarningsAdapter$$Lambda$2(CohostingShareEarningsAdapter cohostingShareEarningsAdapter) {
        this.arg$1 = cohostingShareEarningsAdapter;
    }

    public static OnCheckedChangeListener lambdaFactory$(CohostingShareEarningsAdapter cohostingShareEarningsAdapter) {
        return new CohostingShareEarningsAdapter$$Lambda$2(cohostingShareEarningsAdapter);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.updateShareButtonAvailability();
    }
}
