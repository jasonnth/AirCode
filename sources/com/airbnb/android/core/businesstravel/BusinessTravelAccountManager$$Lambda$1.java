package com.airbnb.android.core.businesstravel;

import com.airbnb.android.core.responses.businesstravel.BusinessTravelEmployeeResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BusinessTravelAccountManager$$Lambda$1 implements Action1 {
    private final BusinessTravelAccountManager arg$1;

    private BusinessTravelAccountManager$$Lambda$1(BusinessTravelAccountManager businessTravelAccountManager) {
        this.arg$1 = businessTravelAccountManager;
    }

    public static Action1 lambdaFactory$(BusinessTravelAccountManager businessTravelAccountManager) {
        return new BusinessTravelAccountManager$$Lambda$1(businessTravelAccountManager);
    }

    public void call(Object obj) {
        this.arg$1.onFetchBusinessTravelEmployeeComplete(((BusinessTravelEmployeeResponse) obj).businessTravelEmployee);
    }
}
