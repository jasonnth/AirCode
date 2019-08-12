package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemandBasedPricingResponse extends BaseResponse {
    @JsonProperty("dynamic_pricing_control")
    DynamicPricingControl pricingControls;

    public DynamicPricingControl getPricingControl() {
        return this.pricingControls;
    }
}
