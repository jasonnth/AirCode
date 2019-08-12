package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.NightsCounterResponse;
import java.lang.reflect.Type;

public class NightsCounterRequest extends BaseRequestV2<NightsCounterResponse> {
    public Type successResponseType() {
        return NightsCounterResponse.class;
    }

    public String getPath() {
        return "night_counts";
    }
}
