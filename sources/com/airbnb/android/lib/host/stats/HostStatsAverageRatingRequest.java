package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class HostStatsAverageRatingRequest extends BaseRequestV2<HostStatsAverageRatingResponse> {
    private final long userId;

    private HostStatsAverageRatingRequest(long userId2) {
        this.userId = userId2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_host_stats");
    }

    public Type successResponseType() {
        return HostStatsAverageRatingResponse.class;
    }

    public String getPath() {
        return "users/" + this.userId;
    }

    public static HostStatsAverageRatingRequest forUserId(long userId2) {
        return new HostStatsAverageRatingRequest(userId2);
    }
}
