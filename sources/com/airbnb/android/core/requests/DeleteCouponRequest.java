package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.PatchBuilder;
import com.airbnb.android.core.requests.CouponRequest.CouponAction;
import com.airbnb.android.core.responses.ReservationResponse;

public class DeleteCouponRequest extends CouponRequest {
    @Deprecated
    DeleteCouponRequest(long reservationId, BaseRequestListener<ReservationResponse> listener) {
        super(reservationId, listener);
    }

    DeleteCouponRequest(long reservationId) {
        super(reservationId);
    }

    /* access modifiers changed from: protected */
    public CouponAction getCouponAction() {
        return CouponAction.Delete;
    }

    public String getBody() {
        return new PatchBuilder().remove("coupon_code").toString();
    }
}
