package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.models.PopularDestinationResponse;
import java.lang.reflect.Type;

public class PopularDestinationsChinaRequest extends BaseRequestV2<PopularDestinationResponse> {
    private static final String PATH = "hot_destinations/";

    public String getPath() {
        return PATH;
    }

    public Type successResponseType() {
        return PopularDestinationResponse.class;
    }

    public long getCacheTimeoutMs() {
        return 604800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 3600000;
    }
}
