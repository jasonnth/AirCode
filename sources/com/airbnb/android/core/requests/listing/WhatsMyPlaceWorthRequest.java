package com.airbnb.android.core.requests.listing;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.listing.WhatsMyPlaceWorthResponse;
import java.lang.reflect.Type;

public class WhatsMyPlaceWorthRequest extends BaseRequestV2<WhatsMyPlaceWorthResponse> {
    private final QueryStrap params;

    public static WhatsMyPlaceWorthRequest forWeek(int capacity, double lat, double lng) {
        return new WhatsMyPlaceWorthRequest(QueryStrap.make().mo7893kv("duration_days", 7).mo7893kv("accommodates", capacity).mo7891kv("lat", lat).mo7891kv("lng", lng));
    }

    private WhatsMyPlaceWorthRequest(QueryStrap params2) {
        this.params = params2;
    }

    public QueryStrap getQueryParams() {
        return this.params.mix(super.getQueryParams());
    }

    public String getPath() {
        return "earnings_estimates";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return WhatsMyPlaceWorthResponse.class;
    }
}
