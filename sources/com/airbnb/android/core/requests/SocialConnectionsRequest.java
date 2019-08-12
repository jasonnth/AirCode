package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.SocialConnectionsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import p032rx.Observer;
import retrofit2.Query;

public class SocialConnectionsRequest extends BaseRequestV2<SocialConnectionsResponse> {
    private final long targetUserId;

    public SocialConnectionsRequest(long targetUserId2, BaseRequestListener<SocialConnectionsResponse> listener) {
        withListener((Observer) listener);
        this.targetUserId = targetUserId2;
    }

    public String getPath() {
        return "social_connections";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.targetUserId).mo7895kv(TimelineRequest.ARG_FORMAT, "default");
    }

    public long getCacheTimeoutMs() {
        return 86400000;
    }

    public Type successResponseType() {
        return SocialConnectionsResponse.class;
    }
}
