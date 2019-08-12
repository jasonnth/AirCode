package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class HostRatingBreakdownRequest extends BaseRequestV2<HostRatingBreakdownResponse> {
    private final long listingId;
    private final QueryStrap strap;

    private HostRatingBreakdownRequest(long listingId2, QueryStrap strap2) {
        this.listingId = listingId2;
        this.strap = strap2;
    }

    public Type successResponseType() {
        return HostRatingBreakdownResponse.class;
    }

    public String getPath() {
        return "listings/" + this.listingId;
    }

    public Collection<Query> getQueryParams() {
        return this.strap;
    }

    public static HostRatingBreakdownRequest forListing(long listingId2) {
        return new HostRatingBreakdownRequest(listingId2, QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_category_ratings"));
    }
}
