package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.ListingLongTermDiscountValues;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LongTermDiscountsConversionResponse extends BaseResponse {
    public double monthlyFactor;
    @JsonProperty("listing_long_term_discount_value")
    public ListingLongTermDiscountValues values;
    public double weeklyFactor;
}
