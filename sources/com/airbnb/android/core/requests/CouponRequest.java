package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public abstract class CouponRequest extends BaseRequestV2<ReservationResponse> {
    private final long reservationId;

    public enum CouponAction {
        Apply,
        Delete
    }

    /* access modifiers changed from: protected */
    public abstract CouponAction getCouponAction();

    @Deprecated
    public static CouponRequest forApply(long reservationId2, String coupon, BaseRequestListener<ReservationResponse> listener) {
        return new ApplyCouponRequest(reservationId2, coupon, listener);
    }

    public static CouponRequest forApply(long reservationId2, String coupon) {
        return new ApplyCouponRequest(reservationId2, coupon);
    }

    @Deprecated
    public static CouponRequest forDelete(long reservationId2, BaseRequestListener<ReservationResponse> listener) {
        return new DeleteCouponRequest(reservationId2, listener);
    }

    public static CouponRequest forDelete(long reservationId2) {
        return new DeleteCouponRequest(reservationId2);
    }

    @Deprecated
    CouponRequest(long reservationId2, BaseRequestListener<ReservationResponse> listener) {
        withListener((Observer) listener);
        this.reservationId = reservationId2;
    }

    CouponRequest(long reservationId2) {
        this.reservationId = reservationId2;
    }

    public String getPath() {
        return "reservations/" + this.reservationId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_p4_checkout");
    }

    public RequestMethod getMethod() {
        return RequestMethod.PATCH;
    }

    public Strap getHeaders() {
        return Strap.make().mo11639kv(ApiRequestHeadersInterceptor.HEADER_METHOD_OVERRIDE, "PATCH");
    }

    public AirResponse<ReservationResponse> transformResponse(AirResponse<ReservationResponse> response) {
        ((ReservationResponse) response.body()).couponAction = getCouponAction();
        return super.transformResponse(response);
    }

    public Type successResponseType() {
        return ReservationResponse.class;
    }
}
