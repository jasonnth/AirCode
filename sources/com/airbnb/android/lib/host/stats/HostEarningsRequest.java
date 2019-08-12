package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public abstract class HostEarningsRequest extends BaseRequestV2<HostEarningsResponse> {
    /* access modifiers changed from: protected */
    public abstract String getPeriodString();

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_host_stats_earnings").mo7895kv("period", getPeriodString());
    }

    public Type successResponseType() {
        return HostEarningsResponse.class;
    }

    public String getPath() {
        return "host_earnings";
    }
}
