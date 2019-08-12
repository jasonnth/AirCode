package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.SpecialOffer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpecialOfferResponse extends BaseResponse {
    @JsonProperty("special_offer")
    public SpecialOffer specialOffer;
}
