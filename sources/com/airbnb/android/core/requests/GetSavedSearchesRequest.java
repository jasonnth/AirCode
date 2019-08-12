package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.responses.GetSavedSearchesResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.Collection;
import retrofit2.Query;

public class GetSavedSearchesRequest extends BaseRequestV2<GetSavedSearchesResponse> {
    private static final int DEFAULT_LIMIT = 10;
    private String format;
    private final int limit;
    private final int offset;

    public static GetSavedSearchesRequest withCityContent() {
        GetSavedSearchesRequest request = new GetSavedSearchesRequest(0, 10);
        request.format = "for_city_content";
        return request;
    }

    public GetSavedSearchesRequest() {
        this(0, 10);
    }

    private GetSavedSearchesRequest(int offset2, int limit2) {
        this.offset = offset2;
        this.limit = limit2;
    }

    public String getPath() {
        return "saved_searches";
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make().mo7893kv(TimelineRequest.ARG_OFFSET, this.offset).mo7893kv(TimelineRequest.ARG_LIMIT, this.limit);
        if (this.format != null) {
            strap.mo7895kv(TimelineRequest.ARG_FORMAT, this.format);
        }
        return strap;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public Type successResponseType() {
        return GetSavedSearchesResponse.class;
    }
}
