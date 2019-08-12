package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CalendarPricingSettingsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class CalendarPricingSettingsRequest extends BaseRequestV2<CalendarPricingSettingsResponse> {
    private final long listingId;

    public static CalendarPricingSettingsRequest forListing(long listingId2) {
        return new CalendarPricingSettingsRequest(listingId2);
    }

    private CalendarPricingSettingsRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "calendar_pricing_settings/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "pricing_settings_for_vh"));
    }

    public Type successResponseType() {
        return CalendarPricingSettingsResponse.class;
    }
}
