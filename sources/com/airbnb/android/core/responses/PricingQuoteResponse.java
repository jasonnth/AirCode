package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.PricingQuote;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PricingQuoteResponse extends BaseResponse {
    public PricingQuote pricingQuote;
    @JsonProperty("pricing_quotes")
    public List<PricingQuote> pricingQuotes;
}
