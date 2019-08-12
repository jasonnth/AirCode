package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.PatchBuilder;
import com.airbnb.android.core.requests.CouponRequest.CouponAction;
import com.airbnb.android.core.responses.ReservationResponse;

public class ApplyCouponRequest extends CouponRequest {
    private final String coupon;

    @Deprecated
    public ApplyCouponRequest(long reservationId, String coupon2, BaseRequestListener<ReservationResponse> listener) {
        super(reservationId, listener);
        this.coupon = coupon2;
    }

    public ApplyCouponRequest(long reservationId, String coupon2) {
        super(reservationId);
        this.coupon = coupon2;
    }

    /* access modifiers changed from: protected */
    public CouponAction getCouponAction() {
        return CouponAction.Apply;
    }

    public String getBody() {
        return new PatchBuilder().add("coupon_code", this.coupon).toString();
    }
}
