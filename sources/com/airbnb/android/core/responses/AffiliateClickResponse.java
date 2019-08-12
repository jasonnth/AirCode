package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.AffiliateClick;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AffiliateClickResponse extends BaseResponse {
    @JsonProperty("affiliate_click")
    public AffiliateClick affiliateClick;
}
