package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.NestedListingsResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class NestedListingsRequest extends BaseRequestV2<NestedListingsResponse> {
    public static NestedListingsRequest forCurrentUser() {
        return new NestedListingsRequest();
    }

    public Type successResponseType() {
        return NestedListingsResponse.class;
    }

    public String getPath() {
        return "nested_listings";
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make();
    }
}
