package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ReviewKeywordsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ReviewKeywordsRequest extends BaseRequestV2<ReviewKeywordsResponse> {
    private static final String FORMAT = "for_mobile_review_search_android";
    private final long listingId;

    public ReviewKeywordsRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("listing_id", this.listingId).mo7895kv(TimelineRequest.ARG_FORMAT, FORMAT);
    }

    public Type successResponseType() {
        return ReviewKeywordsResponse.class;
    }

    public String getPath() {
        return "review_keywords";
    }
}
