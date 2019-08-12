package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.GovernmentIdResultsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;

public class GetGovernmentIdResultsRequest extends BaseRequestV2<GovernmentIdResultsResponse> {
    private final QueryStrap queryStrap;

    public GetGovernmentIdResultsRequest(long userId) {
        this.queryStrap = QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_user").mo7893kv(TimelineRequest.ARG_LIMIT, 1).mo7894kv("user_id", userId);
    }

    public String getPath() {
        return "government_id_results";
    }

    public QueryStrap getQueryParams() {
        return this.queryStrap;
    }

    public RequestMethod getMethod() {
        return RequestMethod.GET;
    }

    public Type successResponseType() {
        return GovernmentIdResultsResponse.class;
    }
}
