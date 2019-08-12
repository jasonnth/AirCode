package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.AccountVerificationSelfieGetResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class AccountVerificationSelfieGetRequest extends BaseRequestV2<AccountVerificationSelfieGetResponse> {
    private final long userId;

    public AccountVerificationSelfieGetRequest(long userId2) {
        this.userId = userId2;
    }

    public String getPath() {
        return "user_selfies";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId).mo7893kv(TimelineRequest.ARG_LIMIT, 1);
    }

    public Type successResponseType() {
        return AccountVerificationSelfieGetResponse.class;
    }
}
