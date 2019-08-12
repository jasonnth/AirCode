package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class HostRecentReviewsPerListingRequest extends BaseRequestV2<HostRecentReviewsPerListingResponse> {
    private final QueryStrap strap;

    public HostRecentReviewsPerListingRequest(QueryStrap strap2) {
        this.strap = strap2;
    }

    public Collection<Query> getQueryParams() {
        return this.strap;
    }

    public static HostRecentReviewsPerListingRequest forUser(long userId) {
        return new HostRecentReviewsPerListingRequest(QueryStrap.make().mo7894kv("user_id", userId).mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_reviews_carousel").mo7895kv("_order", "by_review_scores"));
    }

    public Type successResponseType() {
        return HostRecentReviewsPerListingResponse.class;
    }

    public String getPath() {
        return "listings";
    }
}
