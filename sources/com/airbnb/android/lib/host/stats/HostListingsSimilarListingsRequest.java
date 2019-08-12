package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class HostListingsSimilarListingsRequest extends BaseRequestV2<HostListingsSimilarListingsResponse> {
    private static final String SIMILAR_LISTINGS_FORMAT = "for_similar_listings_by_user";
    private final QueryStrap strap;

    private HostListingsSimilarListingsRequest(int offset, int limit, QueryStrap strap2) {
        this.strap = strap2.mo7893kv(TimelineRequest.ARG_OFFSET, offset).mo7893kv(TimelineRequest.ARG_LIMIT, limit);
    }

    public Collection<Query> getQueryParams() {
        return this.strap;
    }

    public Type successResponseType() {
        return HostListingsSimilarListingsResponse.class;
    }

    public String getPath() {
        return "listings";
    }

    public static HostListingsSimilarListingsRequest forUser(User user, int offset, int limit) {
        return new HostListingsSimilarListingsRequest(offset, limit, QueryStrap.make().mo7894kv("user_id", user.getId()).mo7895kv(TimelineRequest.ARG_FORMAT, SIMILAR_LISTINGS_FORMAT));
    }

    public static HostListingsSimilarListingsRequest forListing(Listing listing) {
        return new HostListingsSimilarListingsRequest(0, 1, QueryStrap.make().mo7894kv("listing_id", listing.getId()).mo7895kv(TimelineRequest.ARG_FORMAT, SIMILAR_LISTINGS_FORMAT));
    }
}
