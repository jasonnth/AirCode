package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.GetUserFlagDetailsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public final class GetUserFlagDetailsRequest extends BaseRequestV2<GetUserFlagDetailsResponse> {
    private static final String ARG_TYPE = "flaggable_type";
    private static final String TYPE_USER_BLOCK = "UserBlock";
    private String type;

    private GetUserFlagDetailsRequest(String type2) {
        this.type = type2;
    }

    public static GetUserFlagDetailsRequest forUserBlock() {
        return new GetUserFlagDetailsRequest(TYPE_USER_BLOCK);
    }

    public String getPath() {
        return "user_flag_details";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(ARG_TYPE, this.type);
    }

    public Type successResponseType() {
        return GetUserFlagDetailsResponse.class;
    }
}
