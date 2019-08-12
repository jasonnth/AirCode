package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.responses.ReviewsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class HostStatsReviewsRequest extends BaseRequestV2<ReviewsResponse> {
    private final QueryStrap strap;

    private HostStatsReviewsRequest(QueryStrap params, int offset, int limit) {
        this.strap = params.mo7895kv("role", TripRole.ROLE_KEY_GUEST).mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_reviews").mo7895kv("_order", "recent").mo7893kv(TimelineRequest.ARG_OFFSET, offset).mo7893kv(TimelineRequest.ARG_LIMIT, limit);
    }

    public Type successResponseType() {
        return ReviewsResponse.class;
    }

    public String getPath() {
        return VerificationsAdapter.VERIFICATION_REVIEWS;
    }

    public Collection<Query> getQueryParams() {
        return this.strap;
    }

    public static HostStatsReviewsRequest forUser(long userId, int offset, int limit) {
        return new HostStatsReviewsRequest(QueryStrap.make().mo7894kv("reviewee_id", userId), offset, limit);
    }

    public static HostStatsReviewsRequest forListing(long listingId, int offset, int limit) {
        return new HostStatsReviewsRequest(QueryStrap.make().mo7894kv("listing_id", listingId), offset, limit);
    }
}
