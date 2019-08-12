package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.TravelCoupon;
import com.google.common.base.Predicate;

final /* synthetic */ class GetTravelCouponResponse$$Lambda$1 implements Predicate {
    private static final GetTravelCouponResponse$$Lambda$1 instance = new GetTravelCouponResponse$$Lambda$1();

    private GetTravelCouponResponse$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return GetTravelCouponResponse.lambda$findFirstValidCoupon$0((TravelCoupon) obj);
    }
}
