package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.CommercialHostInfoResponse;
import java.lang.reflect.Type;

public class CommercialHostInfosRequest extends BaseRequestV2<CommercialHostInfoResponse> {
    private static final String PARAM_USER_ID = "user_id";
    private final long userId;

    public static CommercialHostInfosRequest forUserId(long userId2) {
        return new CommercialHostInfosRequest(userId2);
    }

    private CommercialHostInfosRequest(long userId2) {
        this.userId = userId2;
    }

    public String getPath() {
        return "commercial_host_infos/";
    }

    public QueryStrap getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId);
    }

    public Type successResponseType() {
        return CommercialHostInfoResponse.class;
    }
}
