package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BusinessTravelInterstitialAdapter$$Lambda$2 implements OnClickListener {
    private final BusinessTravelInterstitialAdapter arg$1;

    private BusinessTravelInterstitialAdapter$$Lambda$2(BusinessTravelInterstitialAdapter businessTravelInterstitialAdapter) {
        this.arg$1 = businessTravelInterstitialAdapter;
    }

    public static OnClickListener lambdaFactory$(BusinessTravelInterstitialAdapter businessTravelInterstitialAdapter) {
        return new BusinessTravelInterstitialAdapter$$Lambda$2(businessTravelInterstitialAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.setIsBusinessTravel(true);
    }
}
