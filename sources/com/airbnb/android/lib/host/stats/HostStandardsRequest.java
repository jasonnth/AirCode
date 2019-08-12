package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class HostStandardsRequest extends BaseRequestV2<HostStandardsResponse> {
    private final long userId;

    private HostStandardsRequest(long userId2) {
        this.userId = userId2;
    }

    public Type successResponseType() {
        return HostStandardsResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_host_stats");
    }

    public String getPath() {
        return "host_standards/" + this.userId;
    }

    public static HostStandardsRequest forUserId(long userId2) {
        return new HostStandardsRequest(userId2);
    }
}
