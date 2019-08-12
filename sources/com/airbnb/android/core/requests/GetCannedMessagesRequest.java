package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CannedMessageResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetCannedMessagesRequest extends BaseRequestV2<CannedMessageResponse> {
    public static final String THREAD_ID = "thread_id";
    private long threadId;

    public GetCannedMessagesRequest(long threadId2) {
        this.threadId = threadId2;
    }

    public String getPath() {
        return "template_messages";
    }

    public Type successResponseType() {
        return CannedMessageResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("thread_id", this.threadId);
    }
}
