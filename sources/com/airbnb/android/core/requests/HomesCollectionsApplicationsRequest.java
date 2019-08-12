package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.HomesCollectionsApplicationsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class HomesCollectionsApplicationsRequest extends BaseRequestV2<HomesCollectionsApplicationsResponse> {
    private final Strap params;

    public static HomesCollectionsApplicationsRequest forListingId(long listingId, long hostId) {
        return new HomesCollectionsApplicationsRequest(new Strap().mo11638kv("listing_id", listingId).mo11638kv("host_id", hostId));
    }

    public static HomesCollectionsApplicationsRequest forListings(long hostId) {
        return new HomesCollectionsApplicationsRequest(new Strap().mo11639kv(TimelineRequest.ARG_FORMAT, "for_invitation_listing_picker").mo11638kv("host_id", hostId));
    }

    private HomesCollectionsApplicationsRequest(Strap params2) {
        this.params = params2;
    }

    public String getPath() {
        return "homes_collections_applications";
    }

    public Type successResponseType() {
        return HomesCollectionsApplicationsResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }
}
