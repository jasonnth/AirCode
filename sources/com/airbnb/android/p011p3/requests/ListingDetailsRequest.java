package com.airbnb.android.p011p3.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

/* renamed from: com.airbnb.android.p3.requests.ListingDetailsRequest */
public class ListingDetailsRequest extends BaseRequestV2<ListingDetailsResponse> {
    private final long listingId;

    public ListingDetailsRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public Type successResponseType() {
        return ListingDetailsResponse.class;
    }

    public long getCacheOnlyTimeoutMs() {
        return 1200000;
    }

    public long getCacheTimeoutMs() {
        return 2419200000L;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_native");
    }

    public String getPath() {
        return "pdp_listing_details/" + this.listingId;
    }
}
