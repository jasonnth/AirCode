package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.responses.InboxSearchResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class InboxSearchRequest extends BaseRequestV2<InboxSearchResponse> {
    private final boolean forPendingFilter;
    private final String query;
    private final InboxType type;

    public InboxSearchRequest(String query2, InboxType inboxType, boolean forPendingFilter2) {
        this.query = query2;
        this.type = inboxType;
        this.forPendingFilter = forPendingFilter2;
    }

    public Type successResponseType() {
        return InboxSearchResponse.class;
    }

    public String getPath() {
        return "inbox_searches";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_inbox_view_android").mo7895kv("selected_inbox_type", this.type.inboxRole).mo7895kv("q", this.query).mo7895kv("role", this.forPendingFilter ? "pending_requests" : "all");
    }

    public long getCacheOnlyTimeoutMs() {
        return 60000;
    }
}
