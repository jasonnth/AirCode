package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BusinessTravelAutoEnrollFragment$$Lambda$1 implements OnClickListener {
    private final BusinessTravelAutoEnrollFragment arg$1;

    private BusinessTravelAutoEnrollFragment$$Lambda$1(BusinessTravelAutoEnrollFragment businessTravelAutoEnrollFragment) {
        this.arg$1 = businessTravelAutoEnrollFragment;
    }

    public static OnClickListener lambdaFactory$(BusinessTravelAutoEnrollFragment businessTravelAutoEnrollFragment) {
        return new BusinessTravelAutoEnrollFragment$$Lambda$1(businessTravelAutoEnrollFragment);
    }

    public void onClick(View view) {
        BusinessTravelAutoEnrollFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
