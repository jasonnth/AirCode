package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.AssociatedGrayUsersResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class AssociatedGrayUsersRequest extends BaseRequestV2<AssociatedGrayUsersResponse> {
    private final long userId;

    public static AssociatedGrayUsersRequest newInstance(long userId2) {
        return new AssociatedGrayUsersRequest(userId2);
    }

    private AssociatedGrayUsersRequest(long userId2) {
        this.userId = userId2;
    }

    public String getPath() {
        return "associated_gray_users";
    }

    public Type successResponseType() {
        return AssociatedGrayUsersResponse.class;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7894kv("user_id", this.userId);
    }
}
