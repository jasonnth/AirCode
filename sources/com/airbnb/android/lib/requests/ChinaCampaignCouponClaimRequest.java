package com.airbnb.android.lib.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.lib.responses.ChinaCampaignCouponClaimResponse;
import java.lang.reflect.Type;

public class ChinaCampaignCouponClaimRequest extends BaseRequestV2<ChinaCampaignCouponClaimResponse> {
    public Type successResponseType() {
        return ChinaCampaignCouponClaimResponse.class;
    }

    public String getPath() {
        return "china_campaign_coupon_claim_operations";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
