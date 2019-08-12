package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.lib.businesstravel.BusinessTravelInterstitialAdapter.BusinessTravelListener;

final /* synthetic */ class BusinessTravelInterstitialFragment$$Lambda$1 implements BusinessTravelListener {
    private final BusinessTravelInterstitialFragment arg$1;

    private BusinessTravelInterstitialFragment$$Lambda$1(BusinessTravelInterstitialFragment businessTravelInterstitialFragment) {
        this.arg$1 = businessTravelInterstitialFragment;
    }

    public static BusinessTravelListener lambdaFactory$(BusinessTravelInterstitialFragment businessTravelInterstitialFragment) {
        return new BusinessTravelInterstitialFragment$$Lambda$1(businessTravelInterstitialFragment);
    }

    public void setIsBusinessTravel(boolean z) {
        BusinessTravelInterstitialFragment.lambda$new$0(this.arg$1, z);
    }
}
