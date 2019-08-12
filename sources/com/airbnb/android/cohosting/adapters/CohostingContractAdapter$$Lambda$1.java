package com.airbnb.android.cohosting.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.cohosting.utils.CohostingUtil;

final /* synthetic */ class CohostingContractAdapter$$Lambda$1 implements OnClickListener {
    private final CohostingContractAdapter arg$1;
    private final int arg$2;

    private CohostingContractAdapter$$Lambda$1(CohostingContractAdapter cohostingContractAdapter, int i) {
        this.arg$1 = cohostingContractAdapter;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(CohostingContractAdapter cohostingContractAdapter, int i) {
        return new CohostingContractAdapter$$Lambda$1(cohostingContractAdapter, i);
    }

    public void onClick(View view) {
        CohostingUtil.goToHelpCenterLink(this.arg$1.context, this.arg$2);
    }
}
