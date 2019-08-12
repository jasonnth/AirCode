package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.LongTermPricingExample;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class LongTermPricingExampleResponse extends BaseResponse {
    @JsonProperty("long_term_pricing_examples")
    public List<LongTermPricingExample> example;

    public LongTermPricingExample getExample() {
        return (LongTermPricingExample) this.example.get(0);
    }
}
