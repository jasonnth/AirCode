package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class DemandCountsRequest extends BaseRequestV2<DemandCountsResponse> {
    private final long userId;

    private DemandCountsRequest(long userId2) {
        this.userId = userId2;
    }

    public static DemandCountsRequest forUserId(long userId2) {
        return new DemandCountsRequest(userId2);
    }

    public Type successResponseType() {
        return DemandCountsResponse.class;
    }

    public String getPath() {
        return "listings";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_host_stats").mo7894kv("user_id", this.userId);
    }
}
