package com.airbnb.android.lib.businesstravel.network;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class BusinessTravelP5PromotionRequest extends BaseRequestV2<BusinessTravelP5PromotionResponse> {
    private static final String REQUEST_FORMAT = "for_mobile_p5";
    private final String confirmationCode;

    public static BusinessTravelP5PromotionRequest forConfirmationCode(String confirmationCode2) {
        return new BusinessTravelP5PromotionRequest(confirmationCode2);
    }

    private BusinessTravelP5PromotionRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public Type successResponseType() {
        return BusinessTravelP5PromotionResponse.class;
    }

    public String getPath() {
        return "business_travel_p5_customizations/" + this.confirmationCode;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, REQUEST_FORMAT);
    }
}
