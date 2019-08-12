package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BusinessTravelWelcomeFragment$$Lambda$3 implements OnClickListener {
    private final BusinessTravelWelcomeFragment arg$1;

    private BusinessTravelWelcomeFragment$$Lambda$3(BusinessTravelWelcomeFragment businessTravelWelcomeFragment) {
        this.arg$1 = businessTravelWelcomeFragment;
    }

    public static OnClickListener lambdaFactory$(BusinessTravelWelcomeFragment businessTravelWelcomeFragment) {
        return new BusinessTravelWelcomeFragment$$Lambda$3(businessTravelWelcomeFragment);
    }

    public void onClick(View view) {
        BusinessTravelWelcomeFragment.lambda$updateContentUI$2(this.arg$1, view);
    }
}
