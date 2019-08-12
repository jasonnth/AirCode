package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.LongTermDiscountsConversionResponse;
import java.lang.reflect.Type;

public class LongTermDiscountsConversionRequest extends BaseRequestV2<LongTermDiscountsConversionResponse> {
    private final long listingId;
    private final double monthlyFactor;
    private final double weeklyFactor;

    public LongTermDiscountsConversionRequest(long listingId2, double weeklyFactor2, double monthlyFactor2) {
        this.listingId = listingId2;
        this.weeklyFactor = weeklyFactor2;
        this.monthlyFactor = monthlyFactor2;
    }

    public AirResponse<LongTermDiscountsConversionResponse> transformResponse(AirResponse<LongTermDiscountsConversionResponse> response) {
        ((LongTermDiscountsConversionResponse) response.body()).weeklyFactor = this.weeklyFactor;
        ((LongTermDiscountsConversionResponse) response.body()).monthlyFactor = this.monthlyFactor;
        return super.transformResponse(response);
    }

    public String getPath() {
        return "listing_long_term_discount_values/" + this.listingId + "/" + this.weeklyFactor + "/" + this.monthlyFactor;
    }

    public Type successResponseType() {
        return LongTermDiscountsConversionResponse.class;
    }
}
