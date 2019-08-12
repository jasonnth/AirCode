package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.TravelCoupon;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class GetTravelCouponResponse extends BaseResponse {
    @JsonProperty("coupons")
    public List<TravelCoupon> travelCoupons;

    public TravelCoupon findFirstValidCoupon() {
        return (TravelCoupon) FluentIterable.from((Iterable<E>) this.travelCoupons).firstMatch(GetTravelCouponResponse$$Lambda$1.lambdaFactory$()).orNull();
    }

    static /* synthetic */ boolean lambda$findFirstValidCoupon$0(TravelCoupon coupon) {
        return coupon.getSavingsAmount() > 0 && coupon.getExpirationDate().isAfter(AirDate.today()) && !coupon.isUsed() && coupon.isActive();
    }
}
