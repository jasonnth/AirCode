package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CohostedListingsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class CohostedListingsRequest extends BaseRequestV2<CohostedListingsResponse> {
    private final Strap queryParams;

    private CohostedListingsRequest(Strap params) {
        this.queryParams = params;
    }

    public static CohostedListingsRequest forUsers(long ownerId, long cohostId) {
        return new CohostedListingsRequest(Strap.make().mo11638kv("user_id", ownerId).mo11638kv("filtered_by_cohost_id", cohostId).mo11639kv(TimelineRequest.ARG_FORMAT, "for_mobile_stats_picker"));
    }

    public String getPath() {
        return "listings";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) this.queryParams);
    }

    public Type successResponseType() {
        return CohostedListingsResponse.class;
    }
}
