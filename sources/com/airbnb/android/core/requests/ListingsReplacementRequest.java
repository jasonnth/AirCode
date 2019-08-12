package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.responses.ListingsReplacementResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class ListingsReplacementRequest extends BaseRequestV2<ListingsReplacementResponse> {
    private final Strap queryParams;

    private ListingsReplacementRequest(Strap params) {
        this.queryParams = params;
    }

    public static ListingsReplacementRequest forGuestRecovery(AirDate checkIn, AirDate checkOut, int guestCount, long listingId, int limit) {
        return new ListingsReplacementRequest(Strap.make().mo11638kv("listing_id", listingId).mo11637kv(FindTweenAnalytics.GUESTS, guestCount).mo11639kv("check_in", checkIn.getIsoDateString()).mo11639kv("check_out", checkOut.getIsoDateString()).mo11640kv("filter_instant_book", true).mo11640kv("filter_same_room_type", true).mo11637kv(TimelineRequest.ARG_LIMIT, limit));
    }

    public String getPath() {
        return "listing_replacements";
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public Type successResponseType() {
        return ListingsReplacementResponse.class;
    }
}
