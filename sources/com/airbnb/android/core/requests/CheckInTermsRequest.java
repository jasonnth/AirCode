package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ListingCheckInOptionsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class CheckInTermsRequest extends BaseRequestV2<ListingCheckInOptionsResponse> {
    private final long listingId;
    private final Strap params;

    private CheckInTermsRequest(long listingId2, Strap params2) {
        this.listingId = listingId2;
        this.params = params2;
    }

    public static CheckInTermsRequest forCheckInTerms(long listingId2) {
        return new CheckInTermsRequest(listingId2, Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "check_in_time_options"));
    }

    public String getPath() {
        return "listings/" + this.listingId;
    }

    public Type successResponseType() {
        return ListingCheckInOptionsResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }
}
