package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.ThreadInboxInformationResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class ThreadInboxInformationRequest extends BaseRequestV2<ThreadInboxInformationResponse> {
    protected final long threadId;

    public ThreadInboxInformationRequest(long threadId2) {
        this.threadId = threadId2;
    }

    public String getPath() {
        return "threads/" + this.threadId;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_inbox_role");
    }

    public Type successResponseType() {
        return ThreadInboxInformationResponse.class;
    }
}
