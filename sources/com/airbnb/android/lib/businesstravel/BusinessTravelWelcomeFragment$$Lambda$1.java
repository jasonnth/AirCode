package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.lib.businesstravel.network.BusinessTravelWelcomeContentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BusinessTravelWelcomeFragment$$Lambda$1 implements Action1 {
    private final BusinessTravelWelcomeFragment arg$1;

    private BusinessTravelWelcomeFragment$$Lambda$1(BusinessTravelWelcomeFragment businessTravelWelcomeFragment) {
        this.arg$1 = businessTravelWelcomeFragment;
    }

    public static Action1 lambdaFactory$(BusinessTravelWelcomeFragment businessTravelWelcomeFragment) {
        return new BusinessTravelWelcomeFragment$$Lambda$1(businessTravelWelcomeFragment);
    }

    public void call(Object obj) {
        BusinessTravelWelcomeFragment.lambda$new$0(this.arg$1, (BusinessTravelWelcomeContentResponse) obj);
    }
}
