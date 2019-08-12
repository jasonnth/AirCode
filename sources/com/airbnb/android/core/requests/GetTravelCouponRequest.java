package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.GetTravelCouponResponse;
import java.lang.reflect.Type;

public class GetTravelCouponRequest extends BaseRequestV2<GetTravelCouponResponse> {
    public String getPath() {
        return "coupons";
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public Type successResponseType() {
        return GetTravelCouponResponse.class;
    }
}
