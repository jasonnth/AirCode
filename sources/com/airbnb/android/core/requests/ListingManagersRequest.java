package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.responses.ListingManagersResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class ListingManagersRequest extends BaseRequestV2<ListingManagersResponse> {
    private final Strap queryParams;

    private ListingManagersRequest(Strap params) {
        this.queryParams = params;
    }

    public static ListingManagersRequest forListing(long listingId) {
        return new ListingManagersRequest(Strap.make().mo11638kv("listing_id", listingId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_manage_listing"));
    }

    public String getPath() {
        return CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public Type successResponseType() {
        return ListingManagersResponse.class;
    }
}
