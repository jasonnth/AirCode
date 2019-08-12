package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.GetHostReferralsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetHostReferralsRequest extends BaseRequestV2<GetHostReferralsResponse> {
    private final long userId;

    public GetHostReferralsRequest(long userId2) {
        this.userId = userId2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId);
    }

    public Type successResponseType() {
        return GetHostReferralsResponse.class;
    }

    public String getPath() {
        return "host_referrals";
    }
}
