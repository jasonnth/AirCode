package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.responses.SmartPromotionCreationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.List;

public class CreateSmartPromotionRequest extends BaseRequestV2<SmartPromotionCreationResponse> {
    private final String endDate;
    private final long listingId;
    private final double priceFactor;
    private final String startDate;

    private static class Body {
        @JsonProperty
        String endDate;
        @JsonProperty
        long listingId;
        @JsonProperty
        double priceFactor;
        @JsonProperty
        String startDate;

        private Body(long listingId2, String startDate2, String endDate2, double priceFactor2) {
            this.listingId = listingId2;
            this.startDate = startDate2;
            this.endDate = endDate2;
            this.priceFactor = priceFactor2;
        }
    }

    private CreateSmartPromotionRequest(long listingId2, String startDate2, String endDate2, double priceFactor2) {
        this.listingId = listingId2;
        this.startDate = startDate2;
        this.endDate = endDate2;
        this.priceFactor = priceFactor2;
    }

    public static CreateSmartPromotionRequest forInsight(Insight insight) {
        List<AirDate> dateList = insight.getConversionPayload().getDateRange();
        return new CreateSmartPromotionRequest(insight.getListingId(), ((AirDate) dateList.get(0)).getIsoDateString(), ((AirDate) dateList.get(1)).getIsoDateString(), insight.getSmartPromotionPriceFactor());
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return SmartPromotionCreationResponse.class;
    }

    public Object getBody() {
        return new Body(this.listingId, this.startDate, this.endDate, this.priceFactor);
    }

    public String getPath() {
        return "smart_promotion_host_promotions";
    }
}
