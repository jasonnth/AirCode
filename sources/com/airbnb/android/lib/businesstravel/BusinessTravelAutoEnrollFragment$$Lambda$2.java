package com.airbnb.android.lib.businesstravel;

import com.airbnb.p027n2.interfaces.LinkOnClickListener;

final /* synthetic */ class BusinessTravelAutoEnrollFragment$$Lambda$2 implements LinkOnClickListener {
    private final BusinessTravelAutoEnrollFragment arg$1;

    private BusinessTravelAutoEnrollFragment$$Lambda$2(BusinessTravelAutoEnrollFragment businessTravelAutoEnrollFragment) {
        this.arg$1 = businessTravelAutoEnrollFragment;
    }

    public static LinkOnClickListener lambdaFactory$(BusinessTravelAutoEnrollFragment businessTravelAutoEnrollFragment) {
        return new BusinessTravelAutoEnrollFragment$$Lambda$2(businessTravelAutoEnrollFragment);
    }

    public void onClickLink(int i) {
        this.arg$1.goToEditProfile();
    }
}
